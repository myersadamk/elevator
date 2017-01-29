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

    /**
     * Verifies the appropriate exception is thrown when MoveCommand is constructed with a null originatingFloor.
     */
    @Test
    public void nullOriginatingFloor()
    {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("originatingFloor: null");
        new MoveCommand(null, 1);
    }

    /**
     * Verifies the appropriate exception is thrown when MoveCommand is constructed with a null destinationFloor.
     */
    @Test
    public void nullDestinationFloor()
    {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("destinationFloor: null");
        new MoveCommand(1, null);
    }

    /**
     * Verifies the appropriate exception is thrown when MoveCommand is constructed with an originatingFloor that is not positive.
     */
    @Test
    public void nonPositiveOriginatingFloor()
    {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("originatingFloor: <= 0");
        new MoveCommand(0, 1);
    }

    /**
     * Verifies the appropriate exception is thrown when MoveCommand is constructed with a destinationFloor that is not positive.
     */
    @Test
    public void nonPositiveDestinationFloor()
    {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("destinationFloor: <= 0");
        new MoveCommand(1, 0);
    }

    /**
     * Verifies the appropriate exception is thrown when MoveCommand is constructed with an equal originatingFloor and destinationFloor.
     */
    @Test
    public void originAndDestinationAreEqual()
    {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("originatingFloor and destinationFloor must be different values");
        new MoveCommand(13, 13);
    }
}