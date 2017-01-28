package elevator.sim;

import elevator.sim.core.Scenario;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Adam on 1/28/2017.
 */
public final class ElevatorSimModeATest
{
    @Test
    public void exampleScenariosTest()
    {
        assertThat(RunElevatorSim.apply(getScenarioFilepath("examplescenarios.txt"), "b"), equalTo(getSolution("examplescenarios.txt", "b")));
    }

//    final URL resource = getClass().getClassLoader().getResource("scenarios/" + resourceFileName);
//        assert resource != null : "Could not find resource: scenarios/" + resourceFileName;
//        return new ScenarioLoader().loadScenariosFromFile(resource.getPath());

    private String getScenarioFilepath(final String scenarioResourceFileName)
    {
        final String resourceFilePath = "scenarios/" + scenarioResourceFileName;
        final URL resource = getClass().getClassLoader().getResource(resourceFilePath);
        assert resource != null : "Could not find resource: " + resourceFilePath;
        return resource.getPath();
    }

    private String getSolution(final String simulationName, final String mode)
    {
        final String separator = FileSystems.getDefault().getSeparator();
        try
        {
            final Path scenarioPath = Paths.get(ClassLoader.getSystemResource("scenarios" + separator + "solutions" + separator + "mode_b" + separator + "examplescenarios.txt").toURI());
            return new String(Files.readAllBytes(scenarioPath), System.getProperty("file.encoding"));
        }
        catch (final URISyntaxException e)
        {
            e.printStackTrace();
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Loads a resource located in the <code>src/resources/scenarios/</code> directory.
     *
     * @param solutionName The name of the resource.
     * @return Non-null but possibly empty ImmutableList of {@linkplain Scenario scenarios} parsed from the given file.
     */
    private String getSolutionPath(final String solutionName, final String mode)
    {
        final String resourceFilePath = "scenarios/solutions/mode_" + mode + "/" + solutionName;
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
