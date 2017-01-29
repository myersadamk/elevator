package elevator.sim;

import elevator.sim.core.Scenario;
import org.junit.Test;
import util.TestScenarios;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Adam on 1/28/2017.
 */
public final class ElevatorSimModeATest
{
    //        assert resource != null : "Could not find resource: scenarios/" + resourceFileName;
    private abstract static class TestPlan
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

    @Test
    public void exampleScenariosTest()
    {
        new TestPlan()
        {
            @Override
            protected String getScenarioName()
            {
                return "examplescenarios.txt";
            }

            @Override
            protected Mode getMode()
            {
                return Mode.B;
            }
        }.runSimulationAndValidateSolution();
//        final String a = TestScenarios.getScenarioPath("examplescenarios.txt").toString();
//        final String b = TestScenarios.getSolutionPath("examplescenarios.txt", "b").toString();

//        assertThat(RunElevatorSim.apply(a, "b"), equalTo(b));
    }

//    final URL resource = getClass().getClassLoader().getResource("scenarios/" + resourceFileName);
//        return new ScenarioLoader().loadScenariosFromFile(resource.getPath());

    private String getScenarioFilepath(final String scenarioResourceFileName)
    {
        final String resourceFilePath = "scenarios/" + scenarioResourceFileName;
        final URL resource = getClass().getClassLoader().getResource(resourceFilePath);
        assert resource != null : "Could not find resource: " + resourceFilePath;
        return resource.getPath();
    }

    /**
     * Loads a resource located in the <code>src/resources/scenarios/</code> directory.
     *
     * @param solutionName The name of the resource.
     * @return Non-null but possibly empty ImmutableList of {@linkplain Scenario scenarios} parsed from the given file.
     */
    private String getSolutionPath(final String solutionName, final String mode)
    {
        final String resourceFilePath = "solutions/mode_" + mode + "/" + solutionName;
        final URL resource = getClass().getClassLoader().getResource(resourceFilePath);
        assert resource != null : "Could not find resource: " + resourceFilePath;

        try (final InputStreamReader reader = new InputStreamReader(new FileInputStream(resource.getFile())))
        {
            final String derp = "";
            final String line = null;
//            while ((line = reader.read()) != null)
//            {
//                derp += line;
//            }
            System.out.println(line);
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }
        return resource.getPath();

//
//        try (final Scanner scanner = new Scanner(resource.openStream()))
//        {
//            return scanner.useDelimiter("\\Z").next().replaceAll("\\r\\n", "");
//        }
//        catch (final IOException e)
//        {
//            e.printStackTrace();
//        }
//        return null;
    }
}
