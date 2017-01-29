package elevator.sim;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;

/**
 * Utility for running {@linkplain ElevatorSim#main(String[])} in a testing harness.
 */
public final class SimRunner
{
    /**
     * Runs {@linkplain ElevatorSim#main(String[])} with the given scenarioPath and mode, capturing the System.out stream and returning it for assertions.
     *
     * @param scenarioPath The full path to the scenario to run (cannot be null).
     * @param mode The {@linkplain Mode} to run the simulator in (cannot be null).
     * @return Non-null, possibly empty String output from the console.
     * @throws IllegalArgumentException if parameter conditions are not met.
     * @throws AssertionError if an exception occurs while interacting with the console streams.
     */
    public static String run(final Path scenarioPath, final Mode mode)
    {
        return run(new String[]{scenarioPath.toString(), mode.name()});
    }

    /**
     * Runs {@linkplain ElevatorSim#main(String[])} with the given arguments, capturing the System.out stream and returning it for assertions.
     *
     * @param args See {@link ElevatorSim#USAGE}.
     * @return Non-null, possibly empty String output from the console.
     * @throws AssertionError if an exception occurs while interacting with the console streams.
     */
    public static String run(final String[] args)
    {
        final PrintStream standardOut = System.out;

        try (final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
             final PrintStream redirectedStandardOut = new PrintStream(byteStream))
        {
            System.setOut(redirectedStandardOut);
            ElevatorSim.main(args);

            System.out.flush();
            return byteStream.toString();
        }
        catch (final IOException exception)
        {
            throw new AssertionError("Unexpected exception occurred while interacting with redirected System.out.", exception);
        }
        finally
        {
            System.setOut(standardOut);
        }
    }

    /**
     * Declared private to prevent instantiation.
     */
    private SimRunner()
    {
    }
}