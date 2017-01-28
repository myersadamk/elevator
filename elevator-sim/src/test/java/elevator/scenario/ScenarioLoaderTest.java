package elevator.scenario;

import com.google.common.collect.ImmutableList;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by Adam on 1/27/2017.
 */
public class ScenarioLoaderTest
{
    @Test
    public void loadSimpleScenario()
    {
        final ImmutableList<Scenario> scenarios = loadScenarioResource("simplescenario.txt");
        assertEquals(1, scenarios.size());
        assertThat(scenarios, Matchers.contains(
                new Scenario(10,
                        ImmutableList.of(
                                new MoveCommmand(10, 8),
                                new MoveCommmand(8, 1))
                )
        ));
    }

    @Test
    public void loadMultipleScenarios()
    {
        final ImmutableList<Scenario> scenarios = loadScenarioResource("multiplescenarios.txt");
        assertEquals(2, scenarios.size());
        assertThat(scenarios, Matchers.contains(
                new Scenario(10,
                        ImmutableList.of(
                                new MoveCommmand(10, 8),
                                new MoveCommmand(8, 1))
                ),
                new Scenario(9,
                        ImmutableList.of(
                                new MoveCommmand(9, 1),
                                new MoveCommmand(1, 5),
                                new MoveCommmand(1, 6),
                                new MoveCommmand(1, 5))
                )
        ));
    }

    private ImmutableList<Scenario> loadScenarioResource(final String resourceFileName)
    {
        return new ScenarioLoader().loadScenariosFromFile(getClass().getClassLoader().getResource("scenarios/" + resourceFileName).getPath());
    }
}
