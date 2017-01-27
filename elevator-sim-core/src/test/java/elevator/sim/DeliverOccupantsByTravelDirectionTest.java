package elevator.sim;

import com.google.common.collect.ImmutableList;
import elevator.sim.strategy.DeliverOccupantsByTravelDirection;
import org.junit.Test;

/**
 * Created by Adam on 1/27/2017.
 */
public class DeliverOccupantsByTravelDirectionTest
{
    @Test
    public void multipleDescendingOccupants()
    {
        final Occupant occupant1 = new Occupant(4, 1);
        final Occupant occupant2 = new Occupant(3, 2);
//        final Occupant occupant3 = new Occupant(1, 5);

        System.out.println(new DeliverOccupantsByTravelDirection().getVisitationOrder(ImmutableList.of(occupant1, occupant2)));
//        System.out.println(new MultipleOccupantStrategy().getVisitationOrder(ImmutableList.of(occupant1, occupant2, occupant3)));

        // expected output: 4, 3, 2, 1
    }

    @Test
    public void multipleAscendingOccupants()
    {
        final Occupant occupant1 = new Occupant(3, 6);
        final Occupant occupant2 = new Occupant(2, 7);
        final Occupant occupant3 = new Occupant(4, 5);
//        final Occupant occupant3 = new Occupant(1, 5);

        System.out.println(new DeliverOccupantsByTravelDirection().getVisitationOrder(ImmutableList.of(occupant1, occupant2, occupant3)));
//        System.out.println(new MultipleOccupantStrategy().getVisitationOrder(ImmutableList.of(occupant1, occupant2, occupant3)));

        // expected output: 4, 3, 2, 1
    }
}
