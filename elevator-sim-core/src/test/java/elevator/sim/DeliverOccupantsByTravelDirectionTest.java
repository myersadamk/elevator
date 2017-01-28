package elevator.sim;

import com.google.common.collect.ImmutableList;
import elevator.sim.strategy.DeliverOccupantsByTravelDirectionGuava;
import elevator.sim.strategy.OccupantDeliveryStrategy;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Adam on 1/27/2017.
 */
//@RunWith(Parameterized.class)
public class DeliverOccupantsByTravelDirectionTest
{
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
//    @Parameterized.Parameters(name = "using {0}")
//    public static Object[] data()
//    {
//        return new Object[]{new DeliverOccupantsByTravelDirectionGuava()};
//    }
////    public static Object[] parameters()
////    {
////        return new Object[]{new DeliverOccupantsByTravelDirection(), new DeliverOccupantsByTravelDirectionGuava()};
////    }
//
//    @Parameterized.Parameter
//    public OccupantDeliveryStrategy strategy;
    public OccupantDeliveryStrategy strategy = new DeliverOccupantsByTravelDirectionGuava();

    @Test
    public void exampleCaseOne()
    {
        assertThat(strategy.getFloorSequence(10,
                ImmutableList.of(
                        new Occupant(8, 1))),
                Matchers.contains(10, 8, 1));
    }

    @Test
    public void exampleCaseTwo()
    {
        assertThat(strategy.getFloorSequence(9,
                ImmutableList.of(
                        new Occupant(1, 5),
                        new Occupant(1, 6),
                        new Occupant(1, 5))),
                Matchers.contains(9, 1, 5, 6));
    }

    @Test
    public void exampleCaseThree()
    {
        assertThat(strategy.getFloorSequence(2,
                ImmutableList.of(
                        new Occupant(4, 1),
                        new Occupant(4, 2),
                        new Occupant(6, 8))),
                Matchers.contains(2, 4, 2, 1, 6, 8));
    }

    @Test
    public void exampleCaseFour()
    {
        System.out.println(strategy.getFloorSequence(3,
                ImmutableList.of(
                        new Occupant(7, 9),
                        new Occupant(3, 7),
                        new Occupant(5, 8),
                        new Occupant(7, 11),
                        new Occupant(11, 1))));
        assertThat(strategy.getFloorSequence(3,
                ImmutableList.of(
                        new Occupant(7, 9),
                        new Occupant(3, 7),
                        new Occupant(5, 8),
                        new Occupant(7, 11),
                        new Occupant(11, 1))),
                Matchers.contains(3, 5, 7, 8, 9, 11, 1));
    }
//    @Test
//    public void multipleDescendingOccupants()
//    {
//        final Occupant occupant1 = new Occupant(4, 1);
//        final Occupant occupant2 = new Occupant(3, 2);
//        assertThat(strategy.getFloorSequence(,
//                ImmutableList.of(
//                        new Occupant(4, 1),
//                        new Occupant(3, 2),
//                        new Occupant(7, 5))),
//                Matchers.contains(7, 5, 4, 3, 2, 1));
//    }
//    @Test
//    public void multipleAscendingOccupants()
//    {
//        assertThat(strategy.getFloorSequence(,
//                ImmutableList.of(
//                        new Occupant(3, 6),
//                        new Occupant(2, 7),
//                        new Occupant(4, 5))),
//                Matchers.contains(2, 3, 4, 5, 6, 7));
//    }
//
//    @Test
//    public void singleAscendingSingleDescending()
//    {
//        System.out.println(new DeliverOccupantsByTravelDirection().getFloorSequence(, ImmutableList.of(
//                new Occupant(3, 6),
//                new Occupant(4, 1))));
//    }
//
//    @Test
//    public void idleTrip()
//    {
//        System.out.println(new DeliverOccupantsByTravelDirection().getFloorSequence(, ImmutableList.of(
//                new Occupant(3, 3))));
//    }
//
}
