package elevator.sim;

import com.google.inject.Inject;
import elevator.strategy.MoveStrategy;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Created by Adam on 1/27/2017.
 */
public final class Elevator
{
    private final MoveStrategy operationStrategy;
    private final OutputStream outputStream;

    @Inject
    public Elevator(final MoveStrategy operationStrategy, final OutputStream outputStream)
    {
        this.operationStrategy = operationStrategy;
        this.outputStream = outputStream;
    }

    public void runScenarios(final List<Scenario> scenarios)
    {
        scenarios.forEach(scenario -> runScenario(scenario));
    }

    public void runScenario(final Scenario scenario)
    {
        final List<Integer> moveSequence = operationStrategy.getMoveSequence(scenario.getMoveCommands());
        final StringBuffer output = new StringBuffer(moveSequence.size() * 4); // * 4 to account for spaces/potentially multi-digit floor values.
        int floorsTravelled = 0;

        for (final Integer move : moveSequence)
        {
            floorsTravelled += move;
            output.append(move).append(' ');
        }
        try (final OutputStreamWriter writer = new OutputStreamWriter(outputStream))
        {
            writer.write(output.toString() + "(" + floorsTravelled + ")");
            writer.flush();
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }
    }
}
