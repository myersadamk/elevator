package elevator.sim;

import com.google.inject.AbstractModule;
import elevator.scenario.ScenarioLoader;
import elevator.sim.strategy.DeliverOccupantsByTravelDirectionGuava;

public final class ElevatorSimulator
{
    public static void main(final String[] args)
    {
        new AbstractModule()
        {
            @Override
            protected void configure()
            {
//                bind(OccupantDeliveryStrategy.class).to(DeliverOccupantsByTravelDirection.class);
            }
        };

        new Elevator(new DeliverOccupantsByTravelDirectionGuava()).runScenarios(new ScenarioLoader().loadScenariosFromFile(""));
    }
}