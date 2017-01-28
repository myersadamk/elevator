package elevator.sim;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Adam on 1/27/2017.
 */
public class ScenarioTest
{
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void nullMoveCommands()
    {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("moveCommands: null");
        new Scenario(null);
    }
}