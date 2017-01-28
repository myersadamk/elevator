package elevator.strategy;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import elevator.sim.core.MoveCommand;

import java.util.List;

/**
 * Created by Adam on 1/27/2017.
 */
public class MoveBySingleRequest implements MoveStrategy
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

        final ImmutableList.Builder<Integer> moveSequenceBuilder = ImmutableList.builder();
        Integer previousDestinationFloor = null;
        for (final MoveCommand moveCommand : moveCommands)
        {
            final Integer originatingFloor = moveCommand.getOriginatingFloor();
            final Integer destinationFloor = moveCommand.getDestinationFloor();

            if (!originatingFloor.equals(previousDestinationFloor))
            {
                moveSequenceBuilder.add(originatingFloor);
            }
            moveSequenceBuilder.add(destinationFloor);
            previousDestinationFloor = destinationFloor;
        }
        return moveSequenceBuilder.build();
    }
}
