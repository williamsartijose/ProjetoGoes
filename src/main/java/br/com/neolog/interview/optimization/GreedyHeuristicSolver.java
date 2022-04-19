package br.com.neolog.interview.optimization;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class GreedyHeuristicSolver
    implements
        Solver
{
    @Override
    public Solution solve(
        final Problem problem )
    {
        checkNotNull( problem, "Argument problem cannot be null." );
        return solveGreedyHeuristic( problem );
    }

    private Solution solveGreedyHeuristic(
        final Problem problem )
    {
        final List<Item> items = new ArrayList<>( problem.getItems() );
        Collections.sort( items, Collections.reverseOrder() );
        final Solution solution = new Solution( problem.getTargetValue() );
        for( final Item item : items ) {
            if( solution.fits( item ) ) {
                solution.add( item );
            }
        }
        return solution;
    }
}
