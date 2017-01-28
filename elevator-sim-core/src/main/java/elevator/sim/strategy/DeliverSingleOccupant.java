package elevator.sim.strategy;

import com.google.common.collect.ImmutableList;
import elevator.sim.scenario.MoveCommand;

import java.util.List;

/**
 * Created by Adam on 1/27/2017.
 */
public class DeliverSingleOccupant implements OccupantDeliveryStrategy
{

    @Override
    public List<Integer> getMoveSequence(final ImmutableList<MoveCommand> moveCommands)
    {
        return null;
    }
}
