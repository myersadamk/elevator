package elevator.sim;

import com.google.inject.Inject;
import elevator.sim.scenario.Scenario;
import elevator.sim.strategy.OccupantDeliveryStrategy;

import java.util.List;

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

    public void runScenarios(final List<Scenario> scenarios)
    {
        scenarios.forEach(scenario -> runScenario(scenario));
    }

    public void runScenario(final Scenario scenario)
    {
        final List<Integer> moveSequence = operationStrategy.getMoveSequence(scenario.getMoveCommands());
        final StringBuffer output = new StringBuffer(moveSequence.size() * 4); // * 4 to account for spaces/potentially multi-digit floor values.
        int floorsTravelled = 0;

        for (final Integer move : moveSequence)
        {
            floorsTravelled += move;
            output.append(move).append(' ');
        }
        System.out.println(output.toString() + "(" + floorsTravelled + ")");
    }
}
