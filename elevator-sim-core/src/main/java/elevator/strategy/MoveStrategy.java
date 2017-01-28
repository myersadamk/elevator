package elevator.strategy;

import com.google.common.collect.ImmutableList;
import elevator.sim.core.MoveCommand;

import java.util.List;

/**
 * Represents a strategy for executing {@linkplain MoveCommand MoveCommands}.
 */
public interface MoveStrategy
{
    /**
     * Translates a series of {@linkplain MoveCommand MoveCommands} into a sequence of Integers representing the order that floors should be visited.
     *
     * @param moveCommands List of MoveCommands to execute.
     * @return Possibly empty ImmutableList of Integers representing the sequence that floors should be visited.
     */
    ImmutableList<Integer> getMoveSequence(final List<MoveCommand> moveCommands);
}
