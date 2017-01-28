package elevator.scenario;

import com.google.common.collect.ImmutableList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Adam on 1/27/2017.
 */
public class ScenarioLoader
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
        catch (final FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }
        return scenarioBuilder.build();
    }

    private Scenario parseLine(final String line)
    {
        final int colonIndex = line.indexOf(':');
        final int originalFloor = Integer.valueOf(line.substring(0, colonIndex));

        final ImmutableList.Builder<MoveCommmand> travelRequestsBuilder = ImmutableList.builder();
        final Matcher matcher = Pattern.compile("(\\d+)\\-(\\d)").matcher(line);

        if (!matcher.find())
        {
            return new Scenario(originalFloor, travelRequestsBuilder.build());
        }

        travelRequestsBuilder.add(new MoveCommmand(originalFloor, Integer.valueOf(matcher.group(1))));
        travelRequestsBuilder.add(new MoveCommmand(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2))));

        while (matcher.find())
        {
            travelRequestsBuilder.add(new MoveCommmand(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2))));
        }

        return new Scenario(originalFloor, travelRequestsBuilder.build());
    }
}
