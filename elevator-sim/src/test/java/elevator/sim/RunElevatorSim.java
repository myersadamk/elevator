package elevator.sim;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;

/**
 * Function for running {@link ElevatorSim#main(String[])}.
 */
public final class RunElevatorSim
{
    public static String apply(final Path scenarioPath, final Mode mode)
    {
        return apply(new String[]{scenarioPath.toString(), mode.name()});
    }

    /**
     * @param args See {@link ElevatorSim#USAGE}.
     * @return Non-null, possibly blank String output from the console.
     * @throws AssertionError if an exception occurs while interacting with the console streams.
     */
    public static String apply(final String[] args)
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
     * Declared private to prevent direct instantiation.
     */
    private RunElevatorSim()
    {
    }
}