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
            if (TravelDirection.IDLE.equals(currentDirection))
            {
                currentDirection = currentFloor < pickUpFloor ? TravelDirection.UP : TravelDirection.DOWN;
            }
            else
            {
                currentDirection = pickUpFloor < dropOffFloor ? TravelDirection.UP : TravelDirection.DOWN;
            }

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
}
