package elevator.sim.core;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Represents an elevator simulation scenario containing a series of move commands.
 */
public final class Scenario
{
    private final ImmutableList<MoveCommand> moveCommands;

    /**
     * Creates a new {@linkplain Scenario}.
     *
     * @param moveCommands List of {@linkplain MoveCommand MoveCommands} for the scenario (cannot be null, may be empty).
     * @throws IllegalArgumentException if parameter conditions are not met.
     */
    public Scenario(final List<MoveCommand> moveCommands)
    {
        Preconditions.checkArgument(moveCommands != null, "moveCommands: null");

        // copyOf is smarter/faster than one might think! https://github.com/google/guava/wiki/ImmutableCollectionsExplained
        //
        // Also, to see why I'm using List as the parameter, see the API docs: https://google.github.io/guava/releases/19.0/api/docs/com/google/common/collect/ImmutableCollection.html
        // "... a parameter type of ImmutableList is generally a nuisance to callers. Instead, accept Iterable and have your method or constructor body pass it to the appropriate copyOf method itself."
        this.moveCommands = ImmutableList.copyOf(moveCommands);
    }

    /**
     * @return Non-null, possibly empty ImmutableList of {@linkplain MoveCommand MoveCommands} for the scenario.
     */
    public ImmutableList<MoveCommand> getMoveCommands()
    {
        return moveCommands;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean equals(final Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Scenario scenario = (Scenario) o;

        return moveCommands != null ? moveCommands.equals(scenario.moveCommands) : scenario.moveCommands == null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int hashCode()
    {
        return moveCommands != null ? moveCommands.hashCode() : 0;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString()
    {
        return "Scenario{" +
                "moveCommands=" + moveCommands +
                '}';
    }
}
