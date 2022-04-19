package br.com.neolog.interview.optimization;

import static br.com.neolog.interview.optimization.OptimizationItems.generateItems;
import static org.assertj.core.api.Assertions.shouldHaveThrown;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.util.Collections;

import org.junit.Test;

public class RouterSolverTest
{

    @Test( expected = NullPointerException.class )
    public void shouldThrowNullPointerExceptionWhenFirstDelegateIsNull()
    {
        new RouterSolver( null, mock( Solver.class ) );
        shouldHaveThrown( NullPointerException.class );
    }

    @Test( expected = NullPointerException.class )
    public void shouldThrowNullPointerExceptionWhenSecondDelegateIsNull()
    {
        new RouterSolver( mock( Solver.class ), null );
        shouldHaveThrown( NullPointerException.class );
    }

    @Test
    public void shouldDelegateToFirstSolverWhenItemsHasLength20()
    {
        final Solver firstSolver = mock( Solver.class );
        final Solver secondSolver = mock( Solver.class );
        final RouterSolver subject = new RouterSolver( firstSolver, secondSolver );
        subject.solve( new Problem( 200, generateItems( Collections.nCopies( 20, 1L ) ) ) );
        verifyZeroInteractions( secondSolver );
        verify( firstSolver ).solve( any() );
    }

    @Test
    public void shouldDelegateToSecondSolverWhenItemsHasLengthGreaterThan20()
    {
        final Solver firstSolver = mock( Solver.class );
        final Solver secondSolver = mock( Solver.class );
        final RouterSolver subject = new RouterSolver( firstSolver, secondSolver );
        subject.solve( new Problem( 200, generateItems( Collections.nCopies( 50, 1L ) ) ) );
        verifyZeroInteractions( firstSolver );
        verify( secondSolver ).solve( any() );
    }
}
