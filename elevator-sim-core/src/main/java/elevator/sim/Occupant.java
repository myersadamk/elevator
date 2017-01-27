package elevator.sim;

/**
 * Created by Adam on 1/27/2017.
 */
public final class Occupant
{

    private final int originatingFloor;
    private final int destinationFloor;

    public Occupant(final int originatingFloor, final int destinationFloor)
    {
        this.originatingFloor = originatingFloor;
        this.destinationFloor = destinationFloor;
    }

    public int getOriginatingFloor()
    {

        return originatingFloor;
    }

    public int getDestinationFloor()
    {
        return destinationFloor;
    }
}
