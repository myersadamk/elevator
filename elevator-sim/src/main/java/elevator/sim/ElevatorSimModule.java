package elevator.sim;

import com.google.inject.AbstractModule;
import elevator.strategy.MoveByRequestsInSameDirection;
import elevator.strategy.MoveBySingleRequest;
import elevator.strategy.MoveStrategy;

import java.io.OutputStreamWriter;

/**
 * Created by Adam on 1/27/2017.
 */
public final class ElevatorSimModule extends AbstractModule
{
    private final Class moveStrategy;

    public ElevatorSimModule(final String strategy)
    {
        switch (strategy.toLowerCase())
        {
            case "a":
                moveStrategy = MoveBySingleRequest.class;
                break;
            case "b":
                moveStrategy = MoveByRequestsInSameDirection.class;
                break;
            default:
                throw new IllegalArgumentException("Invalid mode specified: " + strategy + ". Valid options include [A, a, B, b].");
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    protected void configure()
    {
        bind(MoveStrategy.class).to(moveStrategy);
        bind(OutputStreamWriter.class).toInstance(new OutputStreamWriter(System.out));
    }
}
