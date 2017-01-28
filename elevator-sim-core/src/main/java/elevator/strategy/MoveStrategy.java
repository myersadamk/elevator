package elevator.strategy;

import com.google.common.collect.ImmutableList;
import elevator.sim.MoveCommand;

import java.util.List;

/**
 * Created by Adam on 1/27/2017.
 */
public interface MoveStrategy
{
    ImmutableList<Integer> getMoveSequence(final List<MoveCommand> moveCommands);
}
