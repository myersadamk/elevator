package elevator.sim;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import util.TestScenarios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Adam on 1/28/2017.
 */
@RunWith(Theories.class)
public final class ElevatorSimTest
{
    @DataPoints
    public static final String[] scenarios = new String[]{"examplescenarios.txt", "emptyscenario.txt", "simplescenario.txt", "multidigitscenario.txt", "multiplescenarios.txt"};

    @DataPoints
    public static final Mode[] modes = Mode.class.getEnumConstants();

    @Theory
    public void exampleScenariosTest(final String scenario, final Mode mode)
    {
        System.out.print("Testing theory on " + scenario + " in mode " + mode.name() + "...");
        final String solution = readSolution(TestScenarios.getSolutionPath(scenario, mode));
        assertThat(RunElevatorSim.apply(TestScenarios.getScenarioPath(scenario), mode), equalTo(solution));
        System.out.print(" Success!" + System.lineSeparator());
    }

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
