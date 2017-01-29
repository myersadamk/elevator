package elevator.sim.scenario;

/**
 * Represents an exception that occurred while loading a scenario file.
 */
public final class ScenarioLoadingException extends RuntimeException
{
    /**
     * {@inheritDoc}
     */
    public ScenarioLoadingException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}
