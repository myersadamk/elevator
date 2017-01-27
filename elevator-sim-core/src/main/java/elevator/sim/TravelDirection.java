package elevator.sim;

import com.google.common.base.Preconditions;

/**
 * Created by Adam on 1/27/2017.
 */
public enum TravelDirection
{
    UP, DOWN, IDLE;

    public boolean floorIsOnRoute(final int fromFloor, final int toFloor)
    {
        switch (this)
        {
            case UP:
            {
                if (fromFloor < toFloor)
                {
                    return true;
                }
                break;
            }
            case DOWN:
                if (fromFloor > toFloor)
                {
                    return true;
                }
                break;
            case IDLE:
            {
                Preconditions.checkState(fromFloor == toFloor, "For TravelDirection.IDLE, fromFloor and toFloor must be equal.");
                return true;
            }
        }
        return false;
    }
}
