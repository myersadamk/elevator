package elevator.sim.core;

import java.util.List;

/**
 * Created by Adam on 1/27/2017.
 */
public interface Elevator
{
    /**
     * Runs a single {@linkplain Scenario}.
     *
     * @param scenario The Scenario to run.
     */
    public void runScenario(Scenario scenario);

    /**
     * Runs the given {@linkplain Scenario Scenarios}.
     *
     * @param scenarios The Scenarios to run.
     */
    void runScenarios(List<Scenario> scenarios);
}
