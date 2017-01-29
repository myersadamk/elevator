package util;

import com.google.common.base.Preconditions;
import elevator.sim.Mode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Contains static utility for reading scenarios and solutions for testing purposes.
 */
public final class Scenarios
{
    // Windows x_x
    private static final int MAX_WINDOWS_PATH_LENGTH = 260;

    /**
     * Contains representations of resources needed for testing, mapping to the testDirectories.properties resource file.
     */
    private enum Resource
    {
        /**
         * Directory containing scenario .txt files.
         */
        SCENARIOS("scenarios_directory"),

        /**
         * Directory containing subdirectories for each mode; these subdirectories contain solutions to the corresponding scenarios in {@linkplain #SCENARIOS}.
         */
        SOLUTIONS("solutions_directory"),

        /**
         * Prefix for the mode-specific scenario solutions. The full directory is expected to have the format "prefix" + "mode name".
         */
        MODE_SUBDIRECTORY_PREFIX("mode_subdirectory_prefix");

        private final Properties testDirectories = new Properties();
        private final String directoryName;

        /**
         * @return Non-null name of the directory corresponding to the resource.
         * @throws ResourceLookupException if a corresponding property does not exist in the testDirectories.properties resource.
         */
        String getDirectoryName()
        {
            final String directory = testDirectories.getProperty(directoryName);
            if (directory == null)
            {
                throw new ResourceLookupException("Could not find matching property for " + this.directoryName + " in testDirectories.properties.");
            }
            return directory;
        }

        /**
         * Constructs a new {@linkplain Resource}
         *
         * @param directoryName The name of the directory corresponding to the resource (cannot be null).
         * @throws AssertionError if parameter conditions are not met.
         * @throws ResourceLookupException if the testDirectories.properties resource cannot be loaded.
         */
        Resource(final String directoryName)
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
                throw new ResourceLookupException("Failed to load testDirectories.properties.", exception);
            }
        }
    }

    /**
     * Gets the path for a scenario.
     *
     * @param scenario The file name for the scenario, including the extension, e.g. "scenario.txt" (cannot be null).
     * @return Non-null Path to the scenario resource file.
     * @throws IllegalArgumentException if parameter conditions are not met.
     */
    public static Path getScenarioPath(final String scenario)
    {
        return getPath(Resource.SCENARIOS.getDirectoryName(), scenario);
    }

    /**
     * Gets the path for a solution.
     *
     * @param scenario The file name for the scenario, including the extension, e.g. "scenario.txt" (cannot be null).
     * @param mode The {@linkplain Mode} corresponding to the desired solution (cannot be null).
     * @return Non-null Path to the scenario resource file.
     * @throws IllegalArgumentException if parameter conditions are not met.
     */
    public static Path getSolutionPath(final String scenario, final Mode mode)
    {
        return getPath(Resource.SOLUTIONS.getDirectoryName(), Resource.MODE_SUBDIRECTORY_PREFIX.getDirectoryName() + mode.name().toLowerCase(), scenario);
    }

    /**
     * Gets the path for a resource file.
     *
     * @param pathComponents The components of the path to get, e.g. /scenario/scenario.txt -> "scenario", "scenario.txt" (cannot be null or contain null values).
     * @return Non-null Path to the resource file.
     * @throws IllegalArgumentException if parameter conditions are not met.
     */
    private static Path getPath(final String... pathComponents)
    {
        Preconditions.checkArgument(pathComponents != null, "pathComponents: null");

        final StringBuffer path = new StringBuffer(MAX_WINDOWS_PATH_LENGTH);
        for (final String pathComponent : pathComponents)
        {
            Preconditions.checkArgument(pathComponent != null, "pathComponent: null (path so far [" + path.toString() + "].");
            path.append(FileSystems.getDefault().getSeparator()).append(pathComponent);
        }

        try
        {
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
    private Scenarios()
    {
    }
}
