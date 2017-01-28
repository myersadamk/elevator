package elevator.sim;

import com.google.inject.AbstractModule;
import elevator.scenario.ScenarioLoader;
import elevator.strategy.MoveByRequestsInSameDirection;

import java.io.BufferedOutputStream;

public final class ElevatorSimulator
{
    public static void main(final String[] args)
    {
        new AbstractModule()
        {
            @Override
            protected void configure()
            {
//                bind(MoveStrategy.class).to(DeliverOccupantsByTravelDirection.class);
            }
        };

        new Elevator(new MoveByRequestsInSameDirection(), new BufferedOutputStream(System.out)).runScenarios(new ScenarioLoader().loadScenariosFromFile(""));
    }
}