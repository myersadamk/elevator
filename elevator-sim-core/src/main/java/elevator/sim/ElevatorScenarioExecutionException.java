package elevator.sim;

/**
 * Represents an exception that occurred during the execution of a {@linkplain Scenario} by the {@linkplain Elevator}.
 */
public final class ElevatorScenarioExecutionException extends RuntimeException
{
    /**
     * {@inheritDoc}
     */
    public ElevatorScenarioExecutionException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}
