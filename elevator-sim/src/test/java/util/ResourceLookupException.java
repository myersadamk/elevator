package util;

/**
 * Created by Adam on 1/28/2017.
 */
public final class ResourceLookupException extends RuntimeException
{
    public ResourceLookupException(final String message)
    {
        super(message);
    }

    public ResourceLookupException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}
