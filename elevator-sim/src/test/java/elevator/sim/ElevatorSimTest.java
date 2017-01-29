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
    public static String[] scenarios = new String[]{"examplescenarios.txt"};

    @DataPoints
//    public static Mode[] modes = new Mode[]{Mode.B};
    public static Mode[] modes = Mode.class.getEnumConstants();

    @Theory
    public void exampleScenariosTest(final String scenario, final Mode mode)
    {
        new TestPlan()
        {
            @Override
            protected String getScenarioName()
            {
                return scenario;
            }

            @Override
            protected Mode getMode()
            {
                return mode;
            }
        }.runSimulationAndValidateSolution();
    }

    private abstract class TestPlan
    {
        protected abstract String getScenarioName();

        protected abstract Mode getMode();

        protected final void runSimulationAndValidateSolution()
        {
            final String solution = readSolution(TestScenarios.getSolutionPath(getScenarioName(), getMode()));
            assertThat(RunElevatorSim.apply(TestScenarios.getScenarioPath(getScenarioName()), getMode()), equalTo(solution));
        }

        private String readSolution(final Path solutionPath)
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
}
