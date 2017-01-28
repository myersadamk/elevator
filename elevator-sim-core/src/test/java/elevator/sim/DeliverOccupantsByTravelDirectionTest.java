package elevator.sim;

import com.google.common.collect.ImmutableList;
import elevator.sim.strategy.DeliverOccupantsByTravelDirection;
import elevator.sim.strategy.DeliverOccupantsByTravelDirectionGuava;
import elevator.sim.strategy.OccupantDeliveryStrategy;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Adam on 1/27/2017.
 */
@RunWith(Parameterized.class)
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
    @Parameterized.Parameters(name = "using {0}")
    public static Object[] parameters()
    {
        return new Object[]{new DeliverOccupantsByTravelDirection(), new DeliverOccupantsByTravelDirectionGuava()};
    }

    @Parameterized.Parameter
    public OccupantDeliveryStrategy strategy;

    @Test
    public void multipleDescendingOccupants()
    {
        final Occupant occupant1 = new Occupant(4, 1);
        final Occupant occupant2 = new Occupant(3, 2);
        assertThat(strategy.getFloorSequence(,
                ImmutableList.of(
                        new Occupant(4, 1),
                        new Occupant(3, 2),
                        new Occupant(7, 5))),
                Matchers.contains(7, 5, 4, 3, 2, 1));
    }

    @Test
    public void multipleAscendingOccupants()
    {
        assertThat(strategy.getFloorSequence(,
                ImmutableList.of(
                        new Occupant(3, 6),
                        new Occupant(2, 7),
                        new Occupant(4, 5))),
                Matchers.contains(2, 3, 4, 5, 6, 7));
    }

    @Test
    public void singleAscendingSingleDescending()
    {
        System.out.println(new DeliverOccupantsByTravelDirection().getFloorSequence(, ImmutableList.of(
                new Occupant(3, 6),
                new Occupant(4, 1))));
    }

    @Test
    public void idleTrip()
    {
        System.out.println(new DeliverOccupantsByTravelDirection().getFloorSequence(, ImmutableList.of(
                new Occupant(3, 3))));
    }
}
