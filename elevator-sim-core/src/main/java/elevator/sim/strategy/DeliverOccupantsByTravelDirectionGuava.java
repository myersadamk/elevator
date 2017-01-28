package elevator.sim.strategy;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import elevator.sim.Occupant;
import elevator.sim.TravelDirection;

import java.util.List;

/**
 * Created by Adam on 1/27/2017.
 */
public final class DeliverOccupantsByTravelDirectionGuava implements OccupantDeliveryStrategy
{
    @Override
    public List<Integer> getFloorSequence(final int currentFloor, final List<Occupant> waitingOccupants)
    {
        if (waitingOccupants.isEmpty())
        {
            return ImmutableList.of();
        }

        TravelDirection currentDirection = TravelDirection.IDLE;
        TravelDirection previousDirection = null;

        final ImmutableList.Builder floorSequenceBuilder = ImmutableList.builder();
        ImmutableSortedSet.Builder directionalTripBuilder = null;

        for (final Occupant occupant : waitingOccupants)
        {
            final int pickUpFloor = occupant.getOriginatingFloor();
            final int dropOffFloor = occupant.getDestinationFloor();

            Preconditions.checkState(pickUpFloor != dropOffFloor,
                    "Cannot have an occupant with the same pick-up and drop-off floor (given [" + pickUpFloor + "] at index [" + waitingOccupants.indexOf(occupant) + "].");

            previousDirection = currentDirection;
            currentDirection = pickUpFloor < dropOffFloor ? TravelDirection.UP : TravelDirection.DOWN;

            if (!previousDirection.equals(currentDirection))
            {
                if (directionalTripBuilder != null)
                {
                    floorSequenceBuilder.addAll(directionalTripBuilder.build());
                }

                switch (currentDirection)
                {
                    case UP:
                        directionalTripBuilder = ImmutableSortedSet.naturalOrder();
                        break;
                    case DOWN:
                        directionalTripBuilder = ImmutableSortedSet.reverseOrder();
                        break;
                }
            }
            directionalTripBuilder.add(pickUpFloor).add(dropOffFloor);
        }

        return floorSequenceBuilder.addAll(directionalTripBuilder.build()).build();
    }

    private TravelDirection pickUpFirstRider(final int currentFloor, final Occupant occupant, ImmutableSortedSet.Builder<Integer> sequenceBuilder)
    {
        final TravelDirection currentTravelDirection = currentFloor < occupant.getOriginatingFloor() ? TravelDirection.UP : TravelDirection.DOWN;
        sequenceBuilder = TravelDirection.UP.equals(currentTravelDirection) ? ImmutableSortedSet.naturalOrder() : ImmutableSortedSet.reverseOrder();
        sequenceBuilder.add(currentFloor);

        return currentTravelDirection;
    }
}
