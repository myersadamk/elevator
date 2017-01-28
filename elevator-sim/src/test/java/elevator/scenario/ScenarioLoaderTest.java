package elevator.scenario;

import com.google.common.collect.ImmutableList;
import elevator.sim.MoveCommand;
import elevator.sim.Scenario;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.net.URL;

import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

/**
 * Verifies that {@linkplain ScenarioLoader} can properly load scenarios from text files.
 */
public final class ScenarioLoaderTest
{
    /**
     * Verifies that the <code>emptyscenario.txt</code> resource is loaded and parsed successfully. Since the file is empty, no {@linkplain Scenario Scenarios} should be returned.
     */
    @Test
    public void loadEmptyScenario()
    {
        assertThat(loadScenarioResource("emptyscenario.txt"), empty());
    }

    /**
     * Verifies that the <code>simplescenario.txt</code> resource is loaded and parsed successfully:
     * <pre>
     *     10:8-1
     * </pre>
     */
    @Test
    public void loadSimpleScenario()
    {
        assertThat(loadScenarioResource("simplescenario.txt"), Matchers.contains(
                new Scenario(
                        ImmutableList.of(
                                new MoveCommand(10, 8),
                                new MoveCommand(8, 1))
                )
        ));
    }

    /**
     * Verifies that the <code>multiplescenarios.txt</code> resource is loaded and parsed successfully:
     * <pre>
     *     10:8-1
     *     9:1-5,1-6,1-5
     * </pre>
     */
    @Test
    public void loadMultipleScenarios()
    {
        assertThat(loadScenarioResource("multiplescenarios.txt"), Matchers.contains(
                new Scenario(
                        ImmutableList.of(
                                new MoveCommand(10, 8),
                                new MoveCommand(8, 1))
                ),
                new Scenario(
                        ImmutableList.of(
                                new MoveCommand(9, 1),
                                new MoveCommand(1, 5),
                                new MoveCommand(1, 6),
                                new MoveCommand(1, 5))
                )
        ));
    }

    /**
     * Loads a resource located in the <code>src/resources/scenarios/</code> directory.
     *
     * @param resourceFileName The name of the resource.
     * @return Non-null but possibly empty ImmutableList of {@linkplain Scenario scenarios} parsed from the given file.
     */
    private ImmutableList<Scenario> loadScenarioResource(final String resourceFileName)
    {
        final URL resource = getClass().getClassLoader().getResource("scenarios/" + resourceFileName);
        assert resource != null : "Could not find resource: scenarios/" + resourceFileName;
        return new ScenarioLoader().loadScenariosFromFile(resource.getPath());
    }
}
