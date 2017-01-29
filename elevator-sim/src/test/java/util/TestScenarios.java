package util;

import elevator.sim.Mode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by Adam on 1/28/2017.
 */
public final class TestScenarios
{
    private enum DirectoryProperties
    {
        SCENARIOS("scenarios_directory"),
        SOLUTIONS("solutions_directory"),
        MODE_SUBDIRECTORY_PREFIX("mode_subdirectory_prefix");

        private final Properties testDirectories = new Properties();

        private final String directoryName;

        String getDirectoryName()
        {
            final String directory = testDirectories.getProperty(directoryName);
            if (directory == null)
            {
                throw new ResourceLookupException("Could not find matching property for " + this.directoryName + " in testDirectories.properties.");
            }
            return directory;
        }

        DirectoryProperties(final String directoryName)
        {
            assert directoryName != null : "directoryName: null";
            this.directoryName = directoryName;

            try
            {
                testDirectories.load(ClassLoader.getSystemResourceAsStream("testDirectories.properties"));
            }
            catch (final IOException exception)
            {
                exception.printStackTrace();
                throw new RuntimeException(exception);
            }
        }
    }

    public static Path getScenarioPath(final String scenario)
    {
        return getPath(DirectoryProperties.SCENARIOS.getDirectoryName(), scenario);
    }

    public static Path getSolutionPath(final String scenario, final Mode mode)
    {
        return getPath(DirectoryProperties.SOLUTIONS.getDirectoryName(), DirectoryProperties.MODE_SUBDIRECTORY_PREFIX.getDirectoryName() + mode.name().toLowerCase(), scenario);
    }

    private static Path getPath(final String... pathComponents)
    {
        final StringBuffer path = new StringBuffer(260);
        for (final String pathComponent : pathComponents)
        {
            path.append(FileSystems.getDefault().getSeparator()).append(pathComponent);
        }

        try
        {
            System.out.println(path.toString());
            final URL resource1 = TestScenarios.class.getClassLoader().getResource(path.toString());
            if (resource1 == null)
            {
                System.out.println("fuck");
            }
            final URL resource = ClassLoader.getSystemResource(path.toString());
            if (resource == null)
            {
                throw new ResourceLookupException("Failed to lookup resource: " + path.toString());
            }
            return Paths.get(resource.toURI());
        }
        catch (final URISyntaxException exception)
        {
            throw new ResourceLookupException("Failed to lookup resource: " + path.toString(), exception);
        }

    }

    /**
     * Private constructor to prevent instantiation.
     */
    private TestScenarios()
    {
    }
}
