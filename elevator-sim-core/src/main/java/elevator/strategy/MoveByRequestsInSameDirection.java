package elevator.strategy;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import elevator.sim.core.MoveCommand;

import java.util.List;

/**
 * Created by Adam on 1/27/2017.
 */
public final class MoveByRequestsInSameDirection implements MoveStrategy
{
    /**
     * {@inheritDoc}
     *
     * @param moveCommands List of MoveCommands to execute (cannot be null, may be empty).
     * @throws IllegalArgumentException if parameter conditions are not met.
     */
    @Override
    public ImmutableList<Integer> getMoveSequence(final List<MoveCommand> moveCommands)
    {
        Preconditions.checkArgument(moveCommands != null, "moveCommands: null");
        if (moveCommands.isEmpty())
        {
            return ImmutableList.of();
        }

        MoveDirection previousDirection = null;
        MoveDirection currentDirection = null;
        int pivotFloor = 0;

        final ImmutableList.Builder cumulativeMoveSequence = ImmutableList.builder();
        ImmutableSortedSet.Builder movesSequencedByCurrentDirection = null;

        for (final MoveCommand moveCommand : moveCommands)
        {
            final int originatingFloor = moveCommand.getOriginatingFloor();
            final int destinationFloor = moveCommand.getDestinationFloor();
            assert originatingFloor != destinationFloor : "moveCommand.getOriginatingFloor() == moveCommand.getDestinationFloor()";

            previousDirection = currentDirection;
            currentDirection = originatingFloor < destinationFloor ? MoveDirection.UP : MoveDirection.DOWN;

            if (!currentDirection.equals(previousDirection))
            {
                if (movesSequencedByCurrentDirection != null)
                {
                    final ImmutableSortedSet<Integer> pendingMoveSequence = movesSequencedByCurrentDirection.build();
                    pivotFloor = pendingMoveSequence.last();
                    cumulativeMoveSequence.addAll(pendingMoveSequence);
                }
                movesSequencedByCurrentDirection = MoveDirection.UP.equals(currentDirection) ? ImmutableSortedSet.naturalOrder() : ImmutableSortedSet.reverseOrder();
            }

            if (pivotFloor != originatingFloor)
            {
                movesSequencedByCurrentDirection.add(originatingFloor);
            }
            movesSequencedByCurrentDirection.add(destinationFloor);
        }

        return cumulativeMoveSequence.addAll(movesSequencedByCurrentDirection.build()).build();
    }
}
