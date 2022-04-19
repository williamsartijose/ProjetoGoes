package br.com.neolog.interview.optimization;

import static br.com.neolog.interview.optimization.OptimizationItems.generateItems;
import static org.assertj.core.api.Assertions.shouldHaveThrown;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class BruteForceSolverTest
{
    private final Solver subject = new BruteForceSolver();

    @Test( expected = NullPointerException.class )
    public void shouldThrowNullPointerExceptionWhenProblemIsNull()
    {
        final Solver suggestionSolver = new BruteForceSolver();
        suggestionSolver.solve( null );
        shouldHaveThrown( NullPointerException.class );
    }

    @Test
    public void shouldSkipAnItemToReachTargetValue()
    {
        final int targetValue = 19;
        final Problem problem = new Problem( targetValue, generateItems( 10, 8, 4, 5 ) );
        final Solution solution = subject.solve( problem );

        assertEquals( targetValue, solution.getReachedValue() );
        assertEquals( 3, solution.getItems().size() );
    }

    @Test
    public void MustReturnsAnEmptySolution()
    {
        final Solver solver = new BruteForceSolver();
        final Solution solution = solver.solve(
            new Problem( 10, Arrays.asList( new Item( 20, 1 ) ) ) );

        assertEquals( 0, solution.getReachedValue() );
        assertEquals( 0, solution.getItems().size() );
    }
}
