package elevator.sim;

import com.google.inject.Inject;
import elevator.sim.strategy.OccupantDeliveryStrategy;

/**
 * Created by Adam on 1/27/2017.
 */
public final class Elevator
{
    private final OccupantDeliveryStrategy operationStrategy;

    @Inject
    public Elevator(final OccupantDeliveryStrategy operationStrategy)
    {
        this.operationStrategy = operationStrategy;
    }

    //    @Inject
//    public void setOperationStrategy(final OccupantDeliveryStrategy operationStrategy)
//    {
//        this.operationStrategy = operationStrategy;
//    }
}
