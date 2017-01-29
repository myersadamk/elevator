package elevator.sim;

import com.google.common.base.Preconditions;

/**
 * Represents valid running modes.
 */
public enum Mode
{
    /**
     * See {@linkplain elevator.sim.core.strategy.MoveBySingleRequest}.
     */
    A,

    /**
     * See {@linkplain elevator.sim.core.strategy.MoveByRequestsInSameDirection}.
     */
    B;

    /**
     * Parses a String into the appropriate mode.
     *
     * @param argument Argument to parse (cannot be null).
     * @return The {@linkplain Mode} corresponding to the argument (cannot be null).
     * @throws IllegalArgumentException if parameter conditions are not met.
     */
    public static Mode parse(final String argument)
    {
        Preconditions.checkArgument(argument != null, "argument: null");
        switch (argument.toLowerCase())
        {
            case "a":
                return Mode.A;
            case "b":
                return Mode.B;
            default:
                throw new IllegalArgumentException("Invalid mode specified: [" + argument + "]. Valid options include [A, a, B, b].");
        }
    }
}
