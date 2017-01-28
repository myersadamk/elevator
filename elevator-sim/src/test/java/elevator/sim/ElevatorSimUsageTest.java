package elevator.sim;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by Adam on 1/28/2017.
 */
public class ElevatorSimUsageTest
{
    @Test
    public void noArgumentsPrintsUsage()
    {
        assertThat(RunElevatorSim.apply(new String[]{}), containsString(ElevatorSim.getUsage()));
    }

    @Test
    public void helpArgumentPrintsUsage()
    {
        assertThat(RunElevatorSim.apply(new String[]{"--help"}), containsString(ElevatorSim.getUsage()));
    }
}