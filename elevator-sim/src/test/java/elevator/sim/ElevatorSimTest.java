package elevator.sim;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import util.Scenarios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Theory for testing all scenarios from the resources directory. Note that the test scenarios covered here are not intended to be exhaustive; more granular testing is done at the component level.
 */
@RunWith(Theories.class)
public final class ElevatorSimTest
{
    @DataPoints
    public static final String[] scenarios = new String[]{"examplescenarios.txt", "emptyscenario.txt", "simplescenario.txt", "multidigitscenario.txt", "multiplescenarios.txt"};

    @DataPoints
    public static final Mode[] modes = Mode.class.getEnumConstants();

    /**
     * Verifies each individual scenario succeeds for each mode.
     *
     * @param scenario The name of the scenario file being run.
     * @param mode The {@linkplain Mode} for the current run.
     */
    @Theory
    public void processScenariosInAllModes(final String scenario, final Mode mode)
    {
        System.out.print("Testing theory on " + scenario + " in mode " + mode.name() + "...");
        final String solution = readSolution(Scenarios.getSolutionPath(scenario, mode));
        assertThat(SimRunner.run(Scenarios.getScenarioPath(scenario), mode), equalTo(solution));
        System.out.print(" Success!" + System.lineSeparator());
    }

    /**
     * Reads the solution at the given path.
     *
     * @param solutionPath Path to the solution to read.
     * @return Non-null, possibly empty String containing the contents of the file, encoded in the System's encoding.
     */
    private static String readSolution(final Path solutionPath)
    {
        try
        {
            return new String(Files.readAllBytes(solutionPath), System.getProperty("file.encoding"));
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
