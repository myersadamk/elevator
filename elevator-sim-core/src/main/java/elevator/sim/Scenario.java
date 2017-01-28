package elevator.sim;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

/**
 * Represents an elevator simulation scenario containing a series of move commands.
 */
public final class Scenario
{
    private final ImmutableList<MoveCommand> moveCommands;

    public Scenario(final ImmutableList<MoveCommand> moveCommands)
    {
        Preconditions.checkArgument(moveCommands != null, "moveCommands: null");
        this.moveCommands = moveCommands;
    }

    public ImmutableList<MoveCommand> getMoveCommands()
    {
        return moveCommands;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Scenario scenario = (Scenario) o;

        return moveCommands != null ? moveCommands.equals(scenario.moveCommands) : scenario.moveCommands == null;
    }

    @Override
    public int hashCode()
    {
        return moveCommands != null ? moveCommands.hashCode() : 0;
    }

    @Override
    public String toString()
    {
        return "Scenario{" +
                "moveCommands=" + moveCommands +
                '}';
    }
}
