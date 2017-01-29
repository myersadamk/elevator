package elevator.sim;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Contains tests for verifying the {@linkplain ElevatorSim#USAGE} can be requested from the command line.
 */
public final class ElevatorSimUsageTest
{
    /**
     * Verifies application usage is printed when no arguments are provided on the command line.
     */
    @Test
    public void noArgumentsPrintsUsage()
    {
        assertThat(SimRunner.run(new String[]{}), containsString(ElevatorSim.getUsage()));
    }

    /**
     * Verifies application usage is printed when the only arguemtn on the command line is '--help'.
     */
    @Test
    public void helpArgumentPrintsUsage()
    {
        assertThat(SimRunner.run(new String[]{"--help"}), containsString(ElevatorSim.getUsage()));
    }
}