package elevator.sim;

import elevator.sim.scenario.ScenarioLoadingException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Contains validation tests for invalid command-line arguments.
 */
public final class ElevatorSimArgumentValidationTest
{
    @Rule
    public ExpectedException exception = ExpectedException.none();

    /**
     * Verifies that passing a single argument (that is not --help) causes the appropriate exception to be thrown.
     */
    @Test
    public void oneArgumentInvalid()
    {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Invalid argument list: [foo]");
        assertThat(SimRunner.run(new String[]{"foo"}), containsString(ElevatorSim.getUsage()));
    }

    /**
     * Verifies that passing an unrecognized mode causes the appropriate exception to be thrown.
     */
    @Test
    public void invalidMode()
    {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Invalid mode specified: [c]. Valid options include [A, a, B, b].");
        SimRunner.run(new String[]{"foo", "c"});
    }

    /**
     * Verifies that passing a bogus file path causes the appropriate exception to be thrown.
     */
    @Test
    public void nonExistentFile()
    {
        exception.expect(ScenarioLoadingException.class);
        exception.expectMessage("Could not load scenario (exception thrown reading 123).");
        SimRunner.run(new String[]{"123", "b"});
    }
}