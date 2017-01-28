package elevator.sim.scenario;

import com.google.common.collect.ImmutableList;
import elevator.sim.core.MoveCommand;
import elevator.sim.core.Scenario;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides functionality for loading {@linkplain Scenario scenarios} from text files.
 */
public final class ScenarioLoader
{
    public ImmutableList<Scenario> loadScenariosFromFile(final String fileName)
    {
        final ImmutableList.Builder<Scenario> scenarioBuilder = ImmutableList.builder();
        try (final BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                scenarioBuilder.add(parseLine(line));
            }
        }
        catch (final FileNotFoundException exception)
        {
            throw new ScenarioLoadingException("Could not load scenario (file not found: " + fileName + ").", exception);
        }
        catch (final IOException exception)
        {
            throw new ScenarioLoadingException("Could not load scenario (exception thrown reading " + fileName + ").", exception);
        }
        return scenarioBuilder.build();
    }

    private Scenario parseLine(final String line)
    {
        final int colonIndex = line.indexOf(':');
        final int originalFloor = Integer.valueOf(line.substring(0, colonIndex));

        final ImmutableList.Builder<MoveCommand> travelRequestsBuilder = ImmutableList.builder();
        final Matcher matcher = Pattern.compile("(\\d+)\\-(\\d+)").matcher(line);

        if (!matcher.find())
        {
            return new Scenario(travelRequestsBuilder.build());
        }

        // To simplify algorithm implementations, a MoveCommand is created from the initial floor to the originating floor of the first parsed MoveCommand. This precludes the need to treat the first floor as a special case.
        travelRequestsBuilder.add(new MoveCommand(originalFloor, Integer.valueOf(matcher.group(1))));

        // Following the MoveCommand from the initial floor to first originating floor move, the rest of the MoveCommand's are parsed in a simple left-to-right fashion.
        travelRequestsBuilder.add(new MoveCommand(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2))));
        while (matcher.find())
        {
            travelRequestsBuilder.add(new MoveCommand(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2))));
        }

        return new Scenario(travelRequestsBuilder.build());
    }
}
