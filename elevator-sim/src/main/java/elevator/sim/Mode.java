package elevator.sim;

/**
 * Created by Adam on 1/28/2017.
 */
public enum Mode
{
    A, B;

    public boolean is(final String argument)
    {
        switch (argument.toUpperCase())
        {
            case "A":
                return Mode.A.equals(this);
            case "B":
                return Mode.B.equals(this);
            default:
                return false;
        }
    }
}
