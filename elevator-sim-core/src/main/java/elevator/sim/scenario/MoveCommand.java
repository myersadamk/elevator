package elevator.sim.scenario;

/**
 * Created by Adam on 1/27/2017.
 */
public class MoveCommand
{
    private final Integer originatingFloor;
    private final Integer destinationFloor;

    public MoveCommand(final Integer originatingFloor, final Integer destinationFloor)
    {
        this.originatingFloor = originatingFloor;
        this.destinationFloor = destinationFloor;
    }

    public Integer getOriginatingFloor()
    {
        return originatingFloor;
    }

    public Integer getDestinationFloor()
    {
        return destinationFloor;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean equals(final Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final MoveCommand that = (MoveCommand) o;

        if (!originatingFloor.equals(that.originatingFloor)) return false;
        return destinationFloor.equals(that.destinationFloor);
    }

    /**
     * @inheritDoc
     */
    @Override
    public int hashCode()
    {
        int result = originatingFloor.hashCode();
        result = 31 * result + destinationFloor.hashCode();
        return result;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString()
    {
        return "MoveCommand{" +
                "originatingFloor=" + originatingFloor +
                ", destinationFloor=" + destinationFloor +
                '}';
    }
}
