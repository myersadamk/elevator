package elevator.sim.core;

import java.util.List;

/**
 * Represents an elevator capable of running {@linkplain Scenario Scenarios}.
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
