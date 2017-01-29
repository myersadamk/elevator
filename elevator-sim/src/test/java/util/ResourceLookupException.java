package util;

/**
 * Represents an exception that occurred while attempting to look-up a resource file.
 */
public final class ResourceLookupException extends RuntimeException
{
    /**
     * {@inheritDoc}
     */
    public ResourceLookupException(final String message)
    {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public ResourceLookupException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}
