package elevator.sim.scenario;

import com.google.common.collect.ImmutableList;
import elevator.sim.core.MoveCommand;
import elevator.sim.core.Scenario;
import org.hamcrest.Matchers;
import org.junit.Test;
import util.Scenarios;

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
        assertThat(loadScenario("emptyscenario.txt"), empty());
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
        assertThat(loadScenario("simplescenario.txt"), Matchers.contains(
                new Scenario(
                        ImmutableList.of(
                                new MoveCommand(10, 8),
                                new MoveCommand(8, 1))
                )
        ));
    }

    /**
     * Verifies that the <code>multidigitscenario.txt</code> resource is loaded and parsed successfully:
     * <pre>
     *     120:33-71,75-94,
     * </pre>
     *
     * @implNote This test covers a bug in the regex where I used \\d rather than \\d+ for the second group match.
     */
    @Test
    public void multiDigitScenario()
    {
        assertThat(loadScenario("multidigitscenario.txt"), Matchers.contains(
                new Scenario(
                        ImmutableList.of(
                                new MoveCommand(120, 33),
                                new MoveCommand(33, 71),
                                new MoveCommand(75, 94))
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
        assertThat(loadScenario("multiplescenarios.txt"), Matchers.contains(
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
     * Loads a scenario resource located in the <code>src/resources/scenarios/</code> directory using the {@linkplain ScenarioLoader}.
     *
     * @param scenarioFileName The name of the scenario resource.
     * @return Non-null but possibly empty ImmutableList of {@linkplain Scenario scenarios} parsed from the given file.
     */
    private ImmutableList<Scenario> loadScenario(final String scenarioFileName)
    {
        return new ScenarioLoader().loadScenariosFromFile(Scenarios.getScenarioPath(scenarioFileName));
    }
}
