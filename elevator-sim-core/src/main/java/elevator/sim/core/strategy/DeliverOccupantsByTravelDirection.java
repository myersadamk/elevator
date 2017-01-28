//package elevator.sim.core.strategy;
//
//import com.google.common.base.Preconditions;
//import com.google.common.collect.Lists;
//import elevator.sim.Occupant;
//import elevator.sim.core.strategy.MoveDirection;
//
//import java.util.List;
//
///**
// * Created by Adam on 1/27/2017.
// */
//public final class DeliverOccupantsByTravelDirection implements MoveStrategy
//{
//    @Override
//    public List<Integer> getMoveSequence(final int currentFloor, final List<Occupant> waitingOccupants)
//    {
//        final List<Integer> visitationOrder = Lists.newArrayListWithCapacity(waitingOccupants.size() * 2);
//        MoveDirection currentDirection = MoveDirection.IDLE;
//        MoveDirection previousDirection = null;
//        int lastDirectionChangeIndex = 0;
//
//        for (final Occupant scenario : waitingOccupants)
//        {
//            final int originatingFloor = scenario.getOriginatingFloor();
//            final int destinationFloor = scenario.getDestinationFloor();
//
//            Preconditions.checkState(originatingFloor != destinationFloor,
//                    "Cannot have an scenario with the same pick-up and drop-off floor (given [" + originatingFloor + "] at index [" + waitingOccupants.indexOf(scenario) + "].");
//
//            previousDirection = currentDirection;
//            currentDirection = originatingFloor < destinationFloor ? MoveDirection.UP : MoveDirection.DOWN;
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
//    private void queueTripInSameDirection(final MoveDirection currentDirection, final int lastDirectionChangeIndex, final List<Integer> queuedFloors, final int originatingFloor, final int destinationFloor)
//    {
//        assert !MoveDirection.IDLE.equals(currentDirection) : "currentDirection: MoveDirection.IDLE";
//        assert lastDirectionChangeIndex >= 0 : "lastDirectionChangeIndex: < 0";
//        assert originatingFloor >= 1 : "originatingFloor: < 1";
//        assert destinationFloor >= 1 : "destinationFloor: < 1";
//
//        queuePickup(currentDirection, lastDirectionChangeIndex, queuedFloors, originatingFloor);
//        queueDropOff(currentDirection, lastDirectionChangeIndex, queuedFloors, destinationFloor);
//    }
//
//    private void queuePickup(final MoveDirection currentDirection, final int lastDirectionChangeIndex, final List<Integer> queuedFloors, final int pickupFloor)
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
//    private void queueDropOff(final MoveDirection currentDirection, final int lastDirectionChangeIndex, final List<Integer> queuedFloors, final int dropOffFloor)
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
