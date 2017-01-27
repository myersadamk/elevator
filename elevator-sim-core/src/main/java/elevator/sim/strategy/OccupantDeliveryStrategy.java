package elevator.sim.strategy;

import elevator.sim.Occupant;

import java.util.List;

/**
 * Created by Adam on 1/27/2017.
 */
public interface OccupantDeliveryStrategy
{
    List<Integer> getVisitationOrder(final List<Occupant> waitingOccupants);
}
