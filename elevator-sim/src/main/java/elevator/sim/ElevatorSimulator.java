package elevator.sim;

import com.google.inject.AbstractModule;
import elevator.sim.strategy.DeliverOccupantsByTravelDirection;
import elevator.sim.strategy.OccupantDeliveryStrategy;

public final class ElevatorSimulator
{
    public static void main(final String[] args)
    {
        new AbstractModule()
        {
            @Override
            protected void configure()
            {
                bind(OccupantDeliveryStrategy.class).to(DeliverOccupantsByTravelDirection.class);
            }
        };
//        final Elevator elevator = new Elevator(new DeliverSingleOccupant());
//        try (final FileReader fileReader = new FileReader(args[0])) {
//
//            final List<Occupant> occupants = Lists.newArrayList();
//            for (final Occupant occupant : occupants) {
//                elevator.queueOccupant(occupant);
//                elevator.run();
//            }
//
//            final Multimap<Integer, Occupant> occupantsByDestinationFloor;
//            occupantsByDestinationFloor = ArrayListMultimap.create();
//
//
//            for (final Map.Entry<Integer, Occupant> entry : occupantsByDestinationFloor.entries()) {
//                final Integer destinationFloor = entry.getKey();
//                final Occupant occupant = entry.getValue();
//
//                elevator.queueOccupant(occupant);
//                elevator.run();
//            }
//
//        } catch (final FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (final IOException e) {
//            e.printStackTrace();
//        }
    }
}