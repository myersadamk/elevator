package elevator.sim;

import elevator.sim.core.MoveCommand;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Verifies parameter validation in the {@linkplain MoveCommand} class.
 */
public final class MoveCommandTest
{
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void nullOriginatingFloor()
    {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("originatingFloor: null");
        new MoveCommand(null, 1);
    }

    @Test
    public void nullDestinationFloor()
    {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("destinationFloor: null");
        new MoveCommand(1, null);
    }

    @Test
    public void nonPositiveOriginatingFloor()
    {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("originatingFloor: <= 0");
        new MoveCommand(0, 1);
    }

    @Test
    public void nonPositiveDestinationFloor()
    {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("destinationFloor: <= 0");
        new MoveCommand(1, 0);
    }

    @Test
    public void originAndDestinationAreEqual()
    {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("originatingFloor and destinationFloor must be different values");
        new MoveCommand(13, 13);
    }
}