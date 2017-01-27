package elevator.sim;

/**
 * Created by Adam on 1/27/2017.
 */
public class Trip
{
    private final TravelDirection travelDirection;
    private final int originatingFloor;
    private final int destinationFloor;

    public Trip(final TravelDirection travelDirection, final int originatingFloor, final int destinationFloor)
    {
        this.travelDirection = travelDirection;
        this.originatingFloor = originatingFloor;
        this.destinationFloor = destinationFloor;
    }

    public TravelDirection getTravelDirection()
    {
        return travelDirection;
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
