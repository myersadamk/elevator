package elevator.sim.strategy;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import elevator.sim.TravelDirection;
import elevator.sim.scenario.MoveCommand;

import java.util.List;

/**
 * Created by Adam on 1/27/2017.
 */
public final class DeliverOccupantsByTravelDirectionGuava implements OccupantDeliveryStrategy
{
    @Override
    public List<Integer> getMoveSequence(final ImmutableList<MoveCommand> moveCommands)
    {
        if (moveCommands.isEmpty())
        {
            return ImmutableList.of();
        }

        TravelDirection previousDirection = null;
        TravelDirection currentDirection = null;
        int pivotFloor = 0;

        final ImmutableList.Builder floorSequenceBuilder = ImmutableList.builder();
        ImmutableSortedSet.Builder directionalTripBuilder = null;

        for (final MoveCommand moveCommand : moveCommands)
        {
            final int originatingFloor = moveCommand.getOriginatingFloor();
            final int destinationFloor = moveCommand.getDestinationFloor();
//            final int originatingFloor = moveCommand.get;
//            final int destinationFloor = occupant.getDestinationFloor();
//
            Preconditions.checkState(originatingFloor != destinationFloor,
                    "Cannot have an scenario with the same pick-up and drop-off floor (given [" + originatingFloor + "] at index [" + moveCommands.indexOf(moveCommand) + "].");

            previousDirection = currentDirection;
            currentDirection = originatingFloor < destinationFloor ? TravelDirection.UP : TravelDirection.DOWN;

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

            if (pivotFloor != originatingFloor)
            {
                directionalTripBuilder.add(originatingFloor);
            }
            directionalTripBuilder.add(destinationFloor);
        }

        return floorSequenceBuilder.addAll(directionalTripBuilder.build()).build();
    }
}
