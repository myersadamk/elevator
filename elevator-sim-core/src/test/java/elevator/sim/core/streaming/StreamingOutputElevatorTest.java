package elevator.sim.core.streaming;

import com.google.common.collect.ImmutableList;
import elevator.sim.core.Elevator;
import elevator.sim.core.ElevatorScenarioExecutionException;
import elevator.sim.core.Scenario;
import elevator.strategy.MoveStrategy;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.*;

/**
 * Verifies the {@linkplain StreamingOutputElevator} writes the appropriate output for a variety of {@linkplain Scenario Scenarios}. In particular, validates that the implementation properly counts the number of floors
 * travelled. Note that it is not necessary to test multiple {@linkplain MoveStrategy MoveStrategies} because the intention is not to test the strategy, just that the output from the elevator simulator is correct.
 * {@link elevator.strategy.MoveByRequestsInSameDirectionTest}
 */
@RunWith(MockitoJUnitRunner.class)
public final class StreamingOutputElevatorTest
{
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private final MoveStrategy mockMoveStrategy = mock(MoveStrategy.class);

    @Mock
    private final OutputStreamWriter mockOutputStreamWriter = mock(OutputStreamWriter.class);

    // Since the MoveStrategy is being mocked, the actual moves in the scenario are arbitrary.
    private static final Scenario DUMMY_SCENARIO = new Scenario(emptyList());

    @Test
    public void emptyScenariosReturnsEarly()
    {
        createElevator().runScenarios(emptyList());
        verifyZeroInteractions(mockMoveStrategy);
        verifyZeroInteractions(mockOutputStreamWriter);
    }

    @Test
    public void emptyMoveCommandReturnsEarly()
    {
        when(mockMoveStrategy.getMoveSequence(emptyList())).thenReturn(ImmutableList.copyOf(emptyList()));

        createElevator().runScenario(DUMMY_SCENARIO);
        verifyZeroInteractions(mockOutputStreamWriter);
    }

    @Test(expected = ElevatorScenarioExecutionException.class)
    public void ioExceptionThrownByWriter()
    {
        when(mockMoveStrategy.getMoveSequence(DUMMY_SCENARIO.getMoveCommands())).thenReturn(ImmutableList.of(10, 8, 1));
        try
        {
            doThrow(new IOException()).when(mockOutputStreamWriter).write(anyString());
        }
        catch (final IOException exception)
        {
            throw new AssertionError("Unexpected exception configuring mock.");
        }
        createElevator().runScenario(DUMMY_SCENARIO);
    }

    /**
     * @implNote This test verifies I'm doing the floor-differential calculation as specified. I'm not doing the full gamut of example scenarios since the integration tests will do that. Instead I've written targeted tests based
     * on specific work-flows.
     */
    @Test
    public void exampleScenarioOne()
    {
        when(mockMoveStrategy.getMoveSequence(DUMMY_SCENARIO.getMoveCommands())).thenReturn(ImmutableList.of(10, 8, 1));

        createElevator().runScenario(DUMMY_SCENARIO);
        verifyWrittenLine("10 8 1 (9)");
    }

    @Test
    public void singleMoveCommand()
    {
        when(mockMoveStrategy.getMoveSequence(DUMMY_SCENARIO.getMoveCommands())).thenReturn(ImmutableList.of(1, 2));

        createElevator().runScenario(DUMMY_SCENARIO);
        verifyWrittenLine("1 2 (1)");
    }

    @Test
    public void multipleAscendingMoveCommands()
    {
        when(mockMoveStrategy.getMoveSequence(DUMMY_SCENARIO.getMoveCommands())).thenReturn(ImmutableList.of(1, 2, 4, 5, 7));

        createElevator().runScenario(DUMMY_SCENARIO);
        verifyWrittenLine("1 2 4 5 7 (6)");
    }

    @Test
    public void multipleDescendingMoveCommands()
    {
        when(mockMoveStrategy.getMoveSequence(DUMMY_SCENARIO.getMoveCommands())).thenReturn(ImmutableList.of(9, 5, 4, 3, 1));

        createElevator().runScenario(DUMMY_SCENARIO);
        verifyWrittenLine("9 5 4 3 1 (8)");
    }

    @Test
    public void ascendingAndDescendingMoveCommands()
    {
        when(mockMoveStrategy.getMoveSequence(DUMMY_SCENARIO.getMoveCommands())).thenReturn(ImmutableList.of(9, 5, 4, 5, 12));

        createElevator().runScenario(DUMMY_SCENARIO);
        verifyWrittenLine("9 5 4 5 12 (13)");
    }

    @Test
    public void multipleScenarios()
    {
        when(mockMoveStrategy.getMoveSequence(DUMMY_SCENARIO.getMoveCommands()))
                .thenReturn(ImmutableList.of(9, 5, 4, 3, 1))
                .thenReturn(ImmutableList.of(1, 8))
                .thenReturn(ImmutableList.of(4, 2, 4, 5));

        createElevator().runScenarios(ImmutableList.of(DUMMY_SCENARIO, DUMMY_SCENARIO, DUMMY_SCENARIO));

        verifyWrittenLine("9 5 4 3 1 (8)");
        verifyWrittenLine("1 8 (7)");
        verifyWrittenLine("4 2 4 5 (5)");
    }

    /**
     * Creates an {@linkplain Elevator} to run the scenarios with. As noted in the class doc, the purpose of these tests is not to test a particular strategy, just the output of the elevator. Therefore the choice of strategy is
     * arbitrary.
     *
     * @return Non-null Elevator for running a simulation.
     */
    private Elevator createElevator()
    {
        return new StreamingOutputElevator(mockMoveStrategy, mockOutputStreamWriter);
    }

    /**
     * Verifies that the given line was written to the {@linkplain #mockOutputStreamWriter}.
     *
     * @param line The line to verify.
     */
    private void verifyWrittenLine(final String line)
    {
        verifyWrittenLines(Collections.singletonList(line));
    }

    /**
     * Verifies that the given lines were written to the {@linkplain #mockOutputStreamWriter} in sequence.
     *
     * @param lines The lines to verify.
     */
    private void verifyWrittenLines(final List<String> lines)
    {
        lines.forEach(line ->
        {
            try
            {
                verify(mockOutputStreamWriter).write(line);
            }
            catch (final IOException exception)
            {
                throw new AssertionError("Unexpected exception verifying line [" + line + "]", exception);
            }
        });
    }
}
