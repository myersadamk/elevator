package elevator.sim;

import elevator.sim.core.MoveCommand;
import elevator.sim.core.Scenario;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Verifies parameter validation in the {@linkplain MoveCommand} class.
 */
public final class ScenarioTest
{
    @Rule
    public ExpectedException exception = ExpectedException.none();

    /**
     * Verifies the appropriate exception is thrown when 'null' is provided to the Scenario constructor.
     */
    @Test
    public void nullMoveCommands()
    {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("moveCommands: null");
        new Scenario(null);
    }
}