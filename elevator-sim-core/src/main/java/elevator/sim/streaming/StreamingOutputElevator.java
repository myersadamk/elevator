package elevator.sim.streaming;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import elevator.sim.Elevator;
import elevator.sim.ElevatorScenarioExecutionException;
import elevator.sim.Scenario;
import elevator.strategy.MoveStrategy;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Created by Adam on 1/27/2017.
 */
public final class StreamingOutputElevator implements Elevator
{
    private final MoveStrategy operationStrategy;
    private final OutputStream outputStream;

    @Inject
    public StreamingOutputElevator(final MoveStrategy operationStrategy, final OutputStream outputStream)
    {
        this.operationStrategy = operationStrategy;
        this.outputStream = outputStream;
    }

    /**
     * Runs the given {@linkplain Scenario Scenarios}, printing the results of the simulation to the associated {@linkplain OutputStream}.
     *
     * @param scenarios The Scenarios to run (cannot be null, may be empty).
     * @throws IllegalArgumentException if parameter conditions are not met.
     */
    @Override
    public void runScenarios(final List<Scenario> scenarios)
    {
        Preconditions.checkArgument(scenarios != null, "scenarios: null");
        if (scenarios.isEmpty())
        {
            return;
        }
        scenarios.forEach(scenario -> runScenario(scenario));
    }

    /**
     * Runs a single {@linkplain Scenario}, printing the results of the simulation to the associated {@linkplain OutputStream}.
     *
     * @param scenario The Scenario to run (cannot be null).
     * @throws IllegalArgumentException if parameter conditions are not met.
     */
    @Override
    public void runScenario(final Scenario scenario)
    {
        Preconditions.checkArgument(scenario != null, "scenario: null");

        final List<Integer> moveSequence = operationStrategy.getMoveSequence(scenario.getMoveCommands());
        final StringBuffer output = new StringBuffer(moveSequence.size() * 4); // * 4 to account for spaces/potentially multi-digit floor values.
        int floorsTravelled = 0;

        for (final Integer move : moveSequence)
        {
            floorsTravelled += move;
            output.append(move).append(' ');
        }
        try (final OutputStreamWriter writer = new OutputStreamWriter(outputStream))
        {
            writer.write(output.toString() + "(" + floorsTravelled + ")");
            writer.flush();
        }
        catch (final IOException exception)
        {
            throw new ElevatorScenarioExecutionException("Exception occurred while executing scenario: " + scenario, exception);
        }
    }
}
