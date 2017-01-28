package elevator.strategy;

import com.google.common.collect.ImmutableList;
import elevator.sim.MoveCommand;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Adam on 1/27/2017.
 */
public class MoveByRequestsInSameDirectionTest
{
    public MoveStrategy strategy = new MoveByRequestsInSameDirection();

    @Test
    public void exampleCaseOne()
    {
        assertThat(strategy.getMoveSequence(
                ImmutableList.of(
                        new MoveCommand(10, 8),
                        new MoveCommand(8, 1))),
                Matchers.contains(10, 8, 1));
    }

    @Test
    public void exampleCaseTwo()
    {
        assertThat(strategy.getMoveSequence(
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
        assertThat(strategy.getMoveSequence(
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
        System.out.println(strategy.getMoveSequence(
                ImmutableList.of(
                        new MoveCommand(3, 7),
                        new MoveCommand(7, 9),
                        new MoveCommand(3, 7),
                        new MoveCommand(5, 8),
                        new MoveCommand(7, 11),
                        new MoveCommand(11, 1))));
        assertThat(strategy.getMoveSequence(
                ImmutableList.of(
                        new MoveCommand(3, 7),
                        new MoveCommand(7, 9),
                        new MoveCommand(3, 7),
                        new MoveCommand(5, 8),
                        new MoveCommand(7, 11),
                        new MoveCommand(11, 1))),
                Matchers.contains(3, 5, 7, 8, 9, 11, 1));
    }

    /**
     * 10:8-1
     * 9:1-5,1-6,1-5
     * 2:4-1,4-2,6-8
     * 3:7-9,3-7,5-8,7-11,11-1
     * 7:11-6,10-5,6-8,7-4,12-7,8-9
     * 6:1-8,6-8
     *
     * @return
     */
    @Test
    public void exampleCaseFive()
    {
        assertThat(strategy.getMoveSequence(
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
        assertThat(strategy.getMoveSequence(
                ImmutableList.of(
                        new MoveCommand(6, 1),
                        new MoveCommand(1, 8),
                        new MoveCommand(6, 8))),
                Matchers.contains(6, 1, 6, 8));
    }

    @Test
    public void multipleDuplicateAscendingMoves()
    {
        assertThat(strategy.getMoveSequence(
                ImmutableList.of(
                        new MoveCommand(1, 6),
                        new MoveCommand(1, 6),
                        new MoveCommand(1, 6))),
                Matchers.contains(1, 6));
    }

    @Test
    public void multipleDuplicateDescendingMoves()
    {
        assertThat(strategy.getMoveSequence(
                ImmutableList.of(
                        new MoveCommand(13, 1),
                        new MoveCommand(13, 1),
                        new MoveCommand(13, 1))),
                Matchers.contains(13, 1));
    }
}
