package elevator.sim.strategy;

import com.google.common.collect.Lists;
import elevator.sim.Occupant;
import elevator.sim.TravelDirection;

import java.util.List;

/**
 * Created by Adam on 1/27/2017.
 */
public final class DeliverOccupantsByTravelDirection implements OccupantDeliveryStrategy
{
    @Override
    public List<Integer> getVisitationOrder(final List<Occupant> waitingOccupants)
    {
        final List<Integer> visitationOrder = Lists.newArrayListWithCapacity(waitingOccupants.size() * 2);
        TravelDirection currentDirection = TravelDirection.IDLE;
        TravelDirection previousDirection = null;
        int lastDirectionChangeIndex = 0;

        for (final Occupant occupant : waitingOccupants)
        {
            final int originatingFloor = occupant.getOriginatingFloor();
            final int destinationFloor = occupant.getDestinationFloor();

            previousDirection = currentDirection;
            currentDirection = originatingFloor == destinationFloor ? TravelDirection.IDLE : originatingFloor < destinationFloor ? TravelDirection.UP : TravelDirection.DOWN;

            if (!previousDirection.equals(currentDirection))
            {
                lastDirectionChangeIndex = visitationOrder.size();
            }

            queueVisitation(currentDirection, lastDirectionChangeIndex, visitationOrder, originatingFloor, destinationFloor);
        }
        return visitationOrder;
    }

    private void queueVisitation(final TravelDirection currentDirection, final int lastDirectionChangeIndex, final List<Integer> visitationOrder, final int originatingFloor, final int destinationFloor)
    {
        if (visitationOrder.isEmpty() || TravelDirection.IDLE.equals(currentDirection))
        {
            visitationOrder.add(originatingFloor);
            visitationOrder.add(destinationFloor);
            return;
        }

        queuePickup(currentDirection, lastDirectionChangeIndex, visitationOrder, originatingFloor);
        queueDropOff(currentDirection, lastDirectionChangeIndex, visitationOrder, destinationFloor);
    }

    private void queuePickup(final TravelDirection currentDirection, final int lastDirectionChangeIndex, final List<Integer> queuedFloors, final int pickupFloor)
    {
        final int queuedFloorCount = queuedFloors.size();
        for (int queuedFloorIndex = lastDirectionChangeIndex; queuedFloorIndex < queuedFloorCount; queuedFloorIndex++)
        {
            final int queuedFloor = queuedFloors.get(queuedFloorIndex);
            if (pickupFloor == queuedFloor)
            {
                break;
            }
            if (currentDirection.floorIsOnRoute(pickupFloor, queuedFloor))
            {
                queuedFloors.add(queuedFloorIndex, pickupFloor);
                break;
            }
        }
    }

    private void queueDropOff(final TravelDirection currentDirection, final int lastDirectionChangeIndex, final List<Integer> queuedFloors, final int dropOffFloor)
    {
        final int lastFloorIndex = queuedFloors.size() - 1;

        // Special case for the scenario where the drop-off floor is higher/lower (depending on direction) than the current final drop-off floor:
        if (!currentDirection.floorIsOnRoute(dropOffFloor, queuedFloors.get(lastFloorIndex)))
        {
            queuedFloors.add(dropOffFloor);
            return;
        }

        for (int queuedFloorIndex = lastFloorIndex; queuedFloorIndex >= lastDirectionChangeIndex; queuedFloorIndex--)
        {
            final int queuedFloor = queuedFloors.get(queuedFloorIndex);
            if (dropOffFloor == queuedFloor)
            {
                break;
            }
            if (currentDirection.floorIsOnRoute(queuedFloor, dropOffFloor))
            {
                queuedFloors.add(queuedFloorIndex + 1, dropOffFloor);
                break;
            }
        }
    }
}
