//package elevator.sim.strategy;
//
//import com.google.common.base.Preconditions;
//import com.google.common.collect.Lists;
//import elevator.sim.Occupant;
//import elevator.sim.TravelDirection;
//
//import java.util.List;
//
///**
// * Created by Adam on 1/27/2017.
// */
//public final class DeliverOccupantsByTravelDirection implements OccupantDeliveryStrategy
//{
//    @Override
//    public List<Integer> getFloorSequence(final int currentFloor, final List<Occupant> waitingOccupants)
//    {
//        final List<Integer> visitationOrder = Lists.newArrayListWithCapacity(waitingOccupants.size() * 2);
//        TravelDirection currentDirection = TravelDirection.IDLE;
//        TravelDirection previousDirection = null;
//        int lastDirectionChangeIndex = 0;
//
//        for (final Occupant occupant : waitingOccupants)
//        {
//            final int originatingFloor = occupant.getOriginatingFloor();
//            final int destinationFloor = occupant.getDestinationFloor();
//
//            Preconditions.checkState(originatingFloor != destinationFloor,
//                    "Cannot have an occupant with the same pick-up and drop-off floor (given [" + originatingFloor + "] at index [" + waitingOccupants.indexOf(occupant) + "].");
//
//            previousDirection = currentDirection;
//            currentDirection = originatingFloor < destinationFloor ? TravelDirection.UP : TravelDirection.DOWN;
//
//            if (previousDirection != currentDirection)
//            {
//                lastDirectionChangeIndex = visitationOrder.size();
//                visitationOrder.add(originatingFloor);
//                visitationOrder.add(destinationFloor);
//                continue;
//            }
//
//            queueTripInSameDirection(currentDirection, lastDirectionChangeIndex, visitationOrder, originatingFloor, destinationFloor);
//        }
//        return visitationOrder;
//    }
//
//    private void queueTripInSameDirection(final TravelDirection currentDirection, final int lastDirectionChangeIndex, final List<Integer> queuedFloors, final int originatingFloor, final int destinationFloor)
//    {
//        assert !TravelDirection.IDLE.equals(currentDirection) : "currentDirection: TravelDirection.IDLE";
//        assert lastDirectionChangeIndex >= 0 : "lastDirectionChangeIndex: < 0";
//        assert originatingFloor >= 1 : "originatingFloor: < 1";
//        assert destinationFloor >= 1 : "destinationFloor: < 1";
//
//        queuePickup(currentDirection, lastDirectionChangeIndex, queuedFloors, originatingFloor);
//        queueDropOff(currentDirection, lastDirectionChangeIndex, queuedFloors, destinationFloor);
//    }
//
//    private void queuePickup(final TravelDirection currentDirection, final int lastDirectionChangeIndex, final List<Integer> queuedFloors, final int pickupFloor)
//    {
//        final int queuedFloorCount = queuedFloors.size();
//        for (int queuedFloorIndex = lastDirectionChangeIndex; queuedFloorIndex < queuedFloorCount; queuedFloorIndex++)
//        {
//            final int queuedFloor = queuedFloors.get(queuedFloorIndex);
//            if (pickupFloor == queuedFloor)
//            {
//                break;
//            }
//            if (currentDirection.floorIsOnRoute(pickupFloor, queuedFloor))
//            {
//                queuedFloors.add(queuedFloorIndex, pickupFloor);
//                break;
//            }
//        }
//    }
//
//    private void queueDropOff(final TravelDirection currentDirection, final int lastDirectionChangeIndex, final List<Integer> queuedFloors, final int dropOffFloor)
//    {
//        final int lastFloorIndex = queuedFloors.size() - 1;
//
//        // Special case for the scenario where the drop-off floor is higher/lower (depending on direction) than the current final drop-off floor:
//        if (!currentDirection.floorIsOnRoute(dropOffFloor, queuedFloors.get(lastFloorIndex)))
//        {
//            queuedFloors.add(dropOffFloor);
//            return;
//        }
//
//        for (int queuedFloorIndex = lastFloorIndex; queuedFloorIndex >= lastDirectionChangeIndex; queuedFloorIndex--)
//        {
//            final int queuedFloor = queuedFloors.get(queuedFloorIndex);
//            if (dropOffFloor == queuedFloor)
//            {
//                break;
//            }
//            if (currentDirection.floorIsOnRoute(queuedFloor, dropOffFloor))
//            {
//                queuedFloors.add(queuedFloorIndex + 1, dropOffFloor);
//                break;
//            }
//        }
//    }
//}
