package elevator.sim.strategy;

import com.google.common.collect.ImmutableList;
import elevator.sim.scenario.MoveCommand;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Adam on 1/27/2017.
 */
//@RunWith(Parameterized.class)
public class DeliverMoveCommandsByTravelDirectionTest
{

    //    @Parameterized.Parameters(name = "using {0}")
//    public static Object[] data()
//    {
//        return new Object[]{new DeliverMoveCommandsByTravelDirectionGuava()};
//    }
////    public static Object[] parameters()
////    {
////        return new Object[]{new DeliverMoveCommandsByTravelDirection(), new DeliverMoveCommandsByTravelDirectionGuava()};
////    }
//
//    @Parameterized.Parameter
//    public OccupantDeliveryStrategy strategy;
    public OccupantDeliveryStrategy strategy = new DeliverOccupantsByTravelDirectionGuava();

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
//    @Test
//    public void multipleDescendingMoveCommands()
//    {
//        final MoveCommand MoveCommand1 = new MoveCommand(4, 1);
//        final MoveCommand MoveCommand2 = new MoveCommand(3, 2);
//        assertThat(strategy.getMoveSequence(,
//                ImmutableList.of(
//                        new MoveCommand(4, 1),
//                        new MoveCommand(3, 2),
//                        new MoveCommand(7, 5))),
//                Matchers.contains(7, 5, 4, 3, 2, 1));
//    }
//    @Test
//    public void multipleAscendingMoveCommands()
//    {
//        assertThat(strategy.getMoveSequence(,
//                ImmutableList.of(
//                        new MoveCommand(3, 6),
//                        new MoveCommand(2, 7),
//                        new MoveCommand(4, 5))),
//                Matchers.contains(2, 3, 4, 5, 6, 7));
//    }
//
//    @Test
//    public void singleAscendingSingleDescending()
//    {
//        System.out.println(new DeliverMoveCommandsByTravelDirection().getMoveSequence(, ImmutableList.of(
//                new MoveCommand(3, 6),
//                new MoveCommand(4, 1))));
//    }
//
//    @Test
//    public void idleTrip()
//    {
//        System.out.println(new DeliverMoveCommandsByTravelDirection().getMoveSequence(, ImmutableList.of(
//                new MoveCommand(3, 3))));
//    }
//
}
