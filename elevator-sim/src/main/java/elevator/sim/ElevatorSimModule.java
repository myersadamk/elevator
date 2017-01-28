package elevator.sim;

import com.google.inject.AbstractModule;

/**
 * Created by Adam on 1/27/2017.
 */
public final class ElevatorSimModule extends AbstractModule
{
    enum Mode
    {
        A("a"), B("b");

        private final String commandLineArgument;

        Mode(final String commandLineArgument)
        {
            this.commandLineArgument = commandLineArgument;
        }
    }

    private final String strategy;

    public ElevatorSimModule(final String strategy)
    {
        this.strategy = strategy;
    }

    /**
     * @inheritDoc
     */
    @Override
    protected void configure()
    {
//        bind(MoveBySingleRequest.class).to(Elevator.class);
    }
}
