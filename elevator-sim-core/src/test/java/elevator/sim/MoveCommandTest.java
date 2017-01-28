package elevator.sim;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Adam on 1/27/2017.
 */
public class MoveCommandTest
{
    @Rule
    public ExpectedException exception = ExpectedException.none();

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