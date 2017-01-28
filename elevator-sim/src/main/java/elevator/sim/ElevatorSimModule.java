package elevator.sim;

import com.google.inject.AbstractModule;
import elevator.sim.core.strategy.MoveByRequestsInSameDirection;
import elevator.sim.core.strategy.MoveBySingleRequest;
import elevator.sim.core.strategy.MoveStrategy;

import java.io.OutputStreamWriter;

/**
 * Created by Adam on 1/27/2017.
 */
public final class ElevatorSimModule extends AbstractModule
{
    private final Class moveStrategy;

    public ElevatorSimModule(final String mode)
    {
        switch (mode.toLowerCase())
        {
            case "a":
                moveStrategy = MoveBySingleRequest.class;
                break;
            case "b":
                moveStrategy = MoveByRequestsInSameDirection.class;
                break;
            default:
                throw new IllegalArgumentException("Invalid mode specified: [" + mode + "]. Valid options include [A, a, B, b].");
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
