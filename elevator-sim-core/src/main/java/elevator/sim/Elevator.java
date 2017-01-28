package elevator.sim;

import java.io.OutputStream;
import java.util.List;

/**
 * Created by Adam on 1/27/2017.
 */
public interface Elevator
{
    /**
     * Runs the given {@linkplain Scenario Scenarios}, printing the results of the simulation to the associated {@linkplain OutputStream}.
     *
     * @param scenarios The Scenarios to run.
     */
    void runScenarios(List<Scenario> scenarios);

    /**
     * Runs a single {@linkplain Scenario}, printing the results of the simulation to the associated {@linkplain OutputStream}.
     *
     * @param scenario The Scenario to run.
     */
    public void runScenario(Scenario scenario);
}
