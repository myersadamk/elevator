package elevator.sim.core.streaming;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import elevator.sim.core.Elevator;
import elevator.sim.core.ElevatorScenarioExecutionException;
import elevator.sim.core.Scenario;
import elevator.sim.core.strategy.MoveStrategy;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of {@linkplain Elevator} that prints the results of running {@linkplain Scenario Scenarios} to an OutputStreamWriter. Each move to a new floor is printed as a digit and proceeded by a space, e.g. <code>3 2 4
 * 5</code>. The output is terminated by a count of how many floors were travelled in total: for this example, there is 1 floor for the move from 3-2, 2 for floors for the move from 2-4, and 1 floor for the move from 4-5,
 * resulting in <code>3 2 4 5 <b>(4)</b></code>. <p />
 * If multiple scenarios are ran, each one is output on a new line.
 */
public final class StreamingOutputElevator implements Elevator
{
    private final MoveStrategy operationStrategy;
    private final OutputStreamWriter outputStreamWriter;

    /**
     * Constructs a new {@linkplain StreamingOutputElevator}.
     *
     * @param moveStrategy The {@linkplain MoveStrategy} to use (cannot be null).
     * @param outputStreamWriter The {@linkplain OutputStreamWriter} to write the results of the scenario to (cannot be null).
     * @throws IllegalArgumentException if parameter conditions are not met.
     */
    @Inject
    public StreamingOutputElevator(final MoveStrategy moveStrategy, final OutputStreamWriter outputStreamWriter)
    {
        Preconditions.checkArgument(moveStrategy != null, "moveStrategy: null");
        Preconditions.checkArgument(outputStreamWriter != null, "outputStreamWriter: null");

        this.operationStrategy = moveStrategy;
        this.outputStreamWriter = outputStreamWriter;
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
        runScenarios(Collections.singletonList(scenario));
    }

    /**
     * Runs the given {@linkplain Scenario Scenarios}, printing the results of the simulation to the associated {@linkplain OutputStream}.
     *
     * @param scenarios The Scenarios to run (cannot be null or contain null values, may be empty).
     * @throws IllegalArgumentException if parameter conditions are not met.
     */
    @Override
    public void runScenarios(final List<Scenario> scenarios)
    {
        Preconditions.checkArgument(scenarios != null, "scenarios: null");

        final int scenariosToRun = scenarios.size();
        for (int scenarioIndex = 0; scenarioIndex < scenariosToRun; scenarioIndex++)
        {
            final Scenario scenario = scenarios.get(scenarioIndex);
            Preconditions.checkArgument(scenario != null, "scenario: null");

            final List<Integer> moveSequence = operationStrategy.getMoveSequence(scenario.getMoveCommands());
            if (moveSequence.isEmpty())
            {
                return;
            }

            final StringBuffer output = new StringBuffer(moveSequence.size() * 4); // * 4 to account for spaces/potentially multi-digit floor values.
            Integer previousFloor = null;
            int floorsTravelled = 0;

            for (final Integer move : moveSequence)
            {
                if (previousFloor != null)
                {
                    floorsTravelled += Math.abs(previousFloor - move);
                }

                output.append(move).append(' ');
                previousFloor = move;
            }
            try
            {
                outputStreamWriter.write(output.toString() + "(" + floorsTravelled + ")" + ((scenarioIndex + 1 == scenariosToRun) ? "" : System.lineSeparator()));
                outputStreamWriter.flush();
            }
            catch (final IOException exception)
            {
                throw new ElevatorScenarioExecutionException("Exception occurred while executing scenario: " + scenario, exception);
            }
        }
    }
}
