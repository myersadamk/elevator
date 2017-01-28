package elevator.strategy;

import com.google.common.collect.ImmutableList;
import elevator.sim.MoveCommand;

import java.util.List;

/**
 * Created by Adam on 1/27/2017.
 */
public class MoveBySingleRequest implements MoveStrategy
{
    @Override
    public ImmutableList<Integer> getMoveSequence(final List<MoveCommand> moveCommands)
    {
        final ImmutableList.Builder<Integer> moveSequenceBuilder = ImmutableList.builder();
        moveCommands.forEach(moveCommand -> moveSequenceBuilder.add(moveCommand.getOriginatingFloor()).add(moveCommand.getDestinationFloor()));
        return moveSequenceBuilder.build();
    }
}
