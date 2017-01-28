package elevator.sim.core;

import com.google.common.base.Preconditions;

/**
 * Represents a command to move from one floor (originating) to another (destination).
 */
public final class MoveCommand
{
    private final Integer originatingFloor;
    private final Integer destinationFloor;

    /**
     * Creates a new {@linkplain MoveCommand}
     *
     * @param originatingFloor The originating floor for the move (must be positive, cannot be null).
     * @param destinationFloor The destination floor for the move (must be positive, cannot be null).
     * @throws IllegalArgumentException if parameter conditions are not met.
     * @apiNote The choice to use Integer rather than primitives comes from the fact that many operations in elevator-sim-core work with Integers; rather than doing a bunch of autoboxing, it is likely more efficient to just
     * maintain the objects.
     */
    public MoveCommand(final Integer originatingFloor, final Integer destinationFloor)
    {
        Preconditions.checkArgument(originatingFloor != null, "originatingFloor: null");
        Preconditions.checkArgument(destinationFloor != null, "destinationFloor: null");
        Preconditions.checkArgument(originatingFloor > 0, "originatingFloor: <= 0");
        Preconditions.checkArgument(destinationFloor > 0, "destinationFloor: <= 0");
        Preconditions.checkArgument(originatingFloor != destinationFloor, "originatingFloor and destinationFloor must be different values");

        this.originatingFloor = originatingFloor;
        this.destinationFloor = destinationFloor;
    }

    /**
     * @return Positive, non-null originating floor for the move.
     */
    public Integer getOriginatingFloor()
    {
        return originatingFloor;
    }

    /**
     * @return Positive, non-null destination floor for the move.
     */
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
