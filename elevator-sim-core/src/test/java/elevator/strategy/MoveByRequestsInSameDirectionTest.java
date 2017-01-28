package elevator.strategy;

import com.google.common.collect.ImmutableList;
import elevator.sim.core.MoveCommand;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Verifies the {@linkplain MoveByRequestsInSameDirectionTest} algorithm implementation operates as expected.
 */
public final class MoveByRequestsInSameDirectionTest
{
    @Test(expected = IllegalArgumentException.class)
    public void nullMoveCommands()
    {
        createMoveStrategy().getMoveSequence(null);
    }

    @Test
    public void exampleCaseOne()
    {
        assertThat(createMoveStrategy().getMoveSequence(
                ImmutableList.of(
                        new MoveCommand(10, 8),
                        new MoveCommand(8, 1))),
                Matchers.contains(10, 8, 1));
    }

    @Test
    public void exampleCaseTwo()
    {
        assertThat(createMoveStrategy().getMoveSequence(
                ImmutableList.of(
                        new MoveCommand(9, 1),
                        new MoveCommand(1, 5),
                        new MoveCommand(1, 6),
                        new MoveCommand(1, 5))),
                Matchers.contains(9, 1, 5, 6));
    }

    @Test
    public void exampleCaseThree()
    {
        assertThat(createMoveStrategy().getMoveSequence(
                ImmutableList.of(
                        new MoveCommand(2, 4),
                        new MoveCommand(4, 1),
                        new MoveCommand(4, 2),
                        new MoveCommand(6, 8))),
                Matchers.contains(2, 4, 2, 1, 6, 8));
    }

    @Test
    public void exampleCaseFour()
    {
        System.out.println(createMoveStrategy().getMoveSequence(
                ImmutableList.of(
                        new MoveCommand(3, 7),
                        new MoveCommand(7, 9),
                        new MoveCommand(3, 7),
                        new MoveCommand(5, 8),
                        new MoveCommand(7, 11),
                        new MoveCommand(11, 1))));
        assertThat(createMoveStrategy().getMoveSequence(
                ImmutableList.of(
                        new MoveCommand(3, 7),
                        new MoveCommand(7, 9),
                        new MoveCommand(3, 7),
                        new MoveCommand(5, 8),
                        new MoveCommand(7, 11),
                        new MoveCommand(11, 1))),
                Matchers.contains(3, 5, 7, 8, 9, 11, 1));
    }

    @Test
    public void exampleCaseFive()
    {
        assertThat(createMoveStrategy().getMoveSequence(
                ImmutableList.of(
                        new MoveCommand(7, 11),
                        new MoveCommand(11, 6),
                        new MoveCommand(10, 5),
                        new MoveCommand(6, 8),
                        new MoveCommand(7, 4),
                        new MoveCommand(12, 7),
                        new MoveCommand(8, 9))),
                Matchers.contains(7, 11, 10, 6, 5, 6, 8, 12, 7, 4, 8, 9));
    }

    @Test
    public void exampleCaseSix()
    {
        assertThat(createMoveStrategy().getMoveSequence(
                ImmutableList.of(
                        new MoveCommand(6, 1),
                        new MoveCommand(1, 8),
                        new MoveCommand(6, 8))),
                Matchers.contains(6, 1, 6, 8));
    }

    @Test
    public void multipleDuplicateAscendingMoves()
    {
        assertThat(createMoveStrategy().getMoveSequence(
                ImmutableList.of(
                        new MoveCommand(1, 6),
                        new MoveCommand(1, 6),
                        new MoveCommand(1, 6))),
                Matchers.contains(1, 6));
    }

    @Test
    public void multipleDuplicateDescendingMoves()
    {
        assertThat(createMoveStrategy().getMoveSequence(
                ImmutableList.of(
                        new MoveCommand(13, 1),
                        new MoveCommand(13, 1),
                        new MoveCommand(13, 1))),
                Matchers.contains(13, 1));
    }

    /**
     * Creates a new {@linkplain MoveByRequestsInSameDirection}. Each test should create a new instance to guarantee test integrity.
     *
     * @return Non-null MoveByRequestsInSameDirection.
     */
    private static MoveByRequestsInSameDirection createMoveStrategy()
    {
        return new MoveByRequestsInSameDirection();
    }
}
