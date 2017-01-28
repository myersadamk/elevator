package elevator.sim.scenario;

import com.google.common.collect.ImmutableList;

/**
 * Created by Adam on 1/27/2017.
 */
public final class Scenario
{
    private final ImmutableList<MoveCommand> moveCommands;

    public Scenario(final Integer originalFloor, final ImmutableList<MoveCommand> moveCommands)
    {
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
