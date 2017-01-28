package elevator.sim.streaming;

import com.google.common.collect.ImmutableList;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import elevator.sim.MoveCommand;
import elevator.strategy.MoveStrategy;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collections;

import static org.mockito.Mockito.*;

/**
 * Created by Adam on 1/27/2017.
 */
public class StreamingOutputElevatorTest
{
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private final MoveStrategy mockMoveStrategy = mock(MoveStrategy.class);

    @Mock
    private final OutputStream mockOutputStream = mock(OutputStream.class);

    @Mock
    private final OutputStreamWriter mockOutputStreamWriter = mock(OutputStreamWriter.class);
    //    private final Module MODULE = new AbstractModule()
//    {
//        @Override
//        protected void configure()
//        {
//            bind(MoveStrategy.class).toInstance(mockMoveStrategy);
//            bind(OutputStream.class).toInstance(mockOutputStream);
//        }
//    };
    private final Injector injector = Guice.createInjector(new AbstractModule()
    {
        @Override
        protected void configure()
        {
            bind(MoveStrategy.class).toInstance(mockMoveStrategy);
            bind(OutputStream.class).toInstance(mockOutputStream);
        }
    });

    @Test
    public void output()
    {
//        final ImmutableList<MoveCommand> moveCommands = ImmutableList.of(new MoveCommand(1, 2));
//        when(mockMoveStrategy.getMoveSequence(Collections.emptyList())).thenReturn(moveCommands);
        BDDMockito.willReturn(ImmutableList.of(new MoveCommand(1, 2))).given(mockMoveStrategy).getMoveSequence(Collections.emptyList());
        try
        {
//            verify(mockOutputStreamWriter, times(1)).write("1 2 (1)");
            verify(mockOutputStream, times(1)).write("1 2 (1)".getBytes());
        }
        catch (final IOException exception)
        {
            // err
            throw new RuntimeException();
        }
        injector.getInstance(StreamingOutputElevator.class).runScenarios(Collections.emptyList());
//        when(mockOutputStream.write());
//        new Elevator(mockMoveStrategy);
//        mockMoveStrategy.
    }
}
