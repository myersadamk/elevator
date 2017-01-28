package elevator.sim;

import elevator.sim.scenario.ScenarioLoadingException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by Adam on 1/28/2017.
 */
public class ElevatorSimArgumentValidationTest
{
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void oneArgumentInvalid()
    {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Invalid argument list: [foo]");
        assertThat(RunElevatorSim.apply(new String[]{"foo"}), containsString(ElevatorSim.getUsage()));
    }

    @Test
    public void invalidMode()
    {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Invalid mode specified: [c]. Valid options include [A, a, B, b].");
        RunElevatorSim.apply(new String[]{"foo", "c"});
    }

    @Test
    public void nonExistantFile()
    {
        exception.expect(ScenarioLoadingException.class);
        exception.expectMessage("Could not load scenario (file not found: 123).");
        RunElevatorSim.apply(new String[]{"123", "b"});
    }
}