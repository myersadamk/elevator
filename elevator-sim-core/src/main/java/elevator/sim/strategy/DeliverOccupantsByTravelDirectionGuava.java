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
    public List<Integer> getFloorSequence(final int originalFloor, final List<Occupant> waitingOccupants)
    {
        if (waitingOccupants.isEmpty())
        {
            return ImmutableList.of();
        }

        TravelDirection previousDirection = null;
        TravelDirection currentDirection = null;
        int pivotFloor = originalFloor;

        final ImmutableList.Builder floorSequenceBuilder = ImmutableList.builder().add(originalFloor);
        ImmutableSortedSet.Builder directionalTripBuilder = null;

        for (final Occupant occupant : waitingOccupants)
        {
            final int pickUpFloor = occupant.getOriginatingFloor();
            final int dropOffFloor = occupant.getDestinationFloor();

            Preconditions.checkState(pickUpFloor != dropOffFloor,
                    "Cannot have an occupant with the same pick-up and drop-off floor (given [" + pickUpFloor + "] at index [" + waitingOccupants.indexOf(occupant) + "].");

            previousDirection = currentDirection;
            currentDirection = pickUpFloor < dropOffFloor ? TravelDirection.UP : TravelDirection.DOWN;

            if (!currentDirection.equals(previousDirection))
            {
                if (directionalTripBuilder != null)
                {
                    final ImmutableSortedSet<Integer> fullTrip = directionalTripBuilder.build();
                    pivotFloor = fullTrip.last();
                    floorSequenceBuilder.addAll(fullTrip);
                }
                directionalTripBuilder = TravelDirection.UP.equals(currentDirection) ? ImmutableSortedSet.naturalOrder() : ImmutableSortedSet.reverseOrder();
            }

            if (pivotFloor != pickUpFloor)
            {
                directionalTripBuilder.add(pickUpFloor);
            }
            directionalTripBuilder.add(dropOffFloor);
        }

        return floorSequenceBuilder.addAll(directionalTripBuilder.build()).build();
    }
}
