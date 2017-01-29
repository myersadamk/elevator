package elevator.sim;

import com.google.inject.AbstractModule;
import elevator.sim.core.Elevator;
import elevator.sim.core.strategy.MoveByRequestsInSameDirection;
import elevator.sim.core.strategy.MoveBySingleRequest;
import elevator.sim.core.strategy.MoveStrategy;
import elevator.sim.core.streaming.StreamingOutputElevator;

import java.io.OutputStreamWriter;

/**
 * Module binding application-appropriate implementations for the elevator-sim-core interfaces.
 */
public final class ElevatorSimModule extends AbstractModule
{
    private final Class moveStrategy;

    public ElevatorSimModule(final Mode mode)
    {
        switch (mode)
        {
            case A:
                moveStrategy = MoveBySingleRequest.class;
                break;
            case B:
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
        bind(Elevator.class).to(StreamingOutputElevator.class);
        bind(MoveStrategy.class).to(moveStrategy);
        bind(OutputStreamWriter.class).toInstance(new OutputStreamWriter(System.out));
    }
}
