package elevator.scenario;

import com.google.common.collect.ImmutableList;

/**
 * Created by Adam on 1/27/2017.
 */
public final class Scenario
{
    private final Integer originalFloor;
    private final ImmutableList<MoveCommmand> moveCommmands;

    public Scenario(final Integer originalFloor, final ImmutableList<MoveCommmand> moveCommmands)
    {
        this.originalFloor = originalFloor;
        this.moveCommmands = moveCommmands;
    }

    public Integer getOriginalFloor()
    {
        return originalFloor;
    }

    public ImmutableList<MoveCommmand> getMoveCommmands()
    {
        return moveCommmands;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Scenario scenario = (Scenario) o;

        if (!originalFloor.equals(scenario.originalFloor)) return false;
        return moveCommmands.equals(scenario.moveCommmands);
    }

    @Override
    public int hashCode()
    {
        int result = originalFloor.hashCode();
        result = 31 * result + moveCommmands.hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return "Scenario{" +
                "originalFloor=" + originalFloor +
                ", moveCommmands=" + moveCommmands +
                '}';
    }
}
