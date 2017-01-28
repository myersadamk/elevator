package elevator.sim;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.inject.Guice;
import com.google.inject.Injector;
import elevator.sim.core.Elevator;
import elevator.sim.core.ElevatorScenarioExecutionException;
import elevator.sim.core.strategy.MoveByRequestsInSameDirection;
import elevator.sim.core.streaming.StreamingOutputElevator;
import elevator.sim.scenario.ScenarioLoader;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Command-line application for running {@linkplain Elevator} simulations.
 * <p />{@value USAGE}
 */
public final class ElevatorSim
{

    private static final String USAGE =
            "Usage: <mode> <filename>\n" + // Note that \n must be used instead of &n or System.lineSeparator() since #USAGE is referred to in JavaDoc.
                    "Modes:    [A,a] indicate MoveBySingleRequest should be used.\n" +
                    "          [B,b] indicate MoveByRequestsInSameDirection should be used.\n" +
                    "Filename: The full path to a file containing the scenarios to run.";

    public static String getUsage()
    {
        return USAGE;
    }

    /**
     * Runs a simulation based on the given <code>args</code>.
     *
     * @param args {@value USAGE}
     * @throws ElevatorScenarioExecutionException if an exception occurs during the simulation.
     */
    public static void main(final String[] args)
    {
        if (args.length == 0 || (args.length == 1 && "--help".equals(args[0])))
        {
            System.out.println(USAGE);
            return;
        }

        final ImmutableList<String> arguments = ImmutableList.copyOf(args);
        Preconditions.checkArgument(args.length == 2, "Invalid argument list: " + arguments + System.lineSeparator());

        final String scenarioFileName = arguments.get(0);
        final String mode = arguments.get(1);

        final Injector injector = Guice.createInjector(new ElevatorSimModule(mode));

        try (final OutputStreamWriter writer = new OutputStreamWriter(System.out))
        {
            new StreamingOutputElevator(new MoveByRequestsInSameDirection(), writer).runScenarios(new ScenarioLoader().loadScenariosFromFile(scenarioFileName));
        }
        catch (final IOException exception)
        {
            throw new ElevatorScenarioExecutionException("Exception occurred while running simulation [" + mode + ", " + scenarioFileName + "]", exception);
        }
    }

}