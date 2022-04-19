package br.com.neolog.interview.optimization;

import static br.com.neolog.interview.optimization.OptimizationItems.generateItems;
import static org.assertj.core.api.Assertions.shouldHaveThrown;
import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

public class GreedyHeuristicSolverTest
{
    @Test( expected = NullPointerException.class )
    public void shouldThrowExceptionWhenItemsIsNull()
    {
        final Solver suggestionSolver = new GreedyHeuristicSolver();
        suggestionSolver.solve( null );
        shouldHaveThrown( NullPointerException.class );
    }

    @Test( expected = IllegalArgumentException.class )
    public void shouldThrowIllegalArgumentExceptionWhenItemsIsEmpty()
    {
        final Solver suggestionSolver = new GreedyHeuristicSolver();
        suggestionSolver.solve( new Problem( 1, Collections.emptyList() ) );
        shouldHaveThrown( IllegalArgumentException.class );
    }

    @Test
    public void shouldReturnAnEmptySolutionWhenNoItemIsFeasibleByItself()
    {
        final Solver solver = new GreedyHeuristicSolver();
        final Solution solution = solver.solve( new Problem( 1, generateItems( 20 ) ) );
        assertEquals( 0, solution.getReachedValue() );
        assertEquals( 0, solution.getItems().size() );
    }

    @Test
    public void shouldSelectSelectMaximumValueAmongElementsWhenOnlyOneItemIsSelectable()
    {
        final Solver suggestionSolver = new GreedyHeuristicSolver();
        final int targetValue = 40;
        final Solution solution = suggestionSolver.solve(
            new Problem( targetValue, generateItems( 30, 20, 20 ) ) );
        assertEquals( 30, solution.getReachedValue() );
        assertEquals( 1, solution.getItems().size() );
    }
}
