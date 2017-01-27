package elevator.sim;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import elevator.sim.strategy.OccupantDeliveryStrategy;

import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Adam on 1/27/2017.
 */
public final class Elevator implements Runnable
{

    private final OccupantDeliveryStrategy operationStrategy;
    private final ListMultimap<Integer, Occupant> pendingOccupantsByOriginatingFloor = ArrayListMultimap.create();
    private final TreeMap<Integer, Collection<Occupant>> currentOccupantsByDestinationFloor = Maps.newTreeMap();
    private int currentFloor = 1;
    private final int nextFloor = 1;

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

    public void queueOccupants(final List<Occupant> occupants)
    {
//        if (operationStrategy.determineNextFloor())
//        occupants.forEach(occupant -> pendingOccupantsByOriginatingFloor.put(occupant.getOriginatingFloor(), occupant));
//        pendingOccupantsByOriginatingFloor.put(occupant.getOriginatingFloor(), occupant);
//        nextFloor = operationStrategy.determineNextFloor(currentOccupantsByDestinationFloor, pendingOccupantsByOriginatingFloor);
    }

//
//    public void load(final int destinationFloor, final Collection<Occupant> occupants) {
//        final Collection<Occupant> destinedOccupants = currentOccupantsByDestinationFloor.putIfAbsent(destinationFloor, occupants);
//        if (destinedOccupants != null) {
//            destinedOccupants.addAll(occupants);
//        }
//    }

    @Override
    public void run()
    {
        currentFloor = nextFloor;
        unload();
        load();
//        nextFloor = operationStrategy.determineNextFloor(currentOccupantsByDestinationFloor, pendingOccupantsByOriginatingFloor);
    }

    private void load()
    {
        final List<Occupant> occupantsToLoad = pendingOccupantsByOriginatingFloor.removeAll(currentFloor);
        if (currentOccupantsByDestinationFloor.containsKey(currentFloor))
        {
            currentOccupantsByDestinationFloor.get(currentFloor).addAll(occupantsToLoad);
        } else
        {
            currentOccupantsByDestinationFloor.put(currentFloor, occupantsToLoad);
        }
    }

    private void unload()
    {
//        pendingOccupantsByOriginatingFloor.asMap().remove(currentFloor);
        currentOccupantsByDestinationFloor.remove(currentFloor);
    }
}
