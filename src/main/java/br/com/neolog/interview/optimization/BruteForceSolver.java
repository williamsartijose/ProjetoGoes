package br.com.neolog.interview.optimization;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

final class BruteForceSolver
    implements
        Solver
{
    @Override
    public Solution solve(
        final Problem problem )
    {
        checkNotNull( problem, "Argument problem cannot be null." );
        final int targetValue = problem.getTargetValue();
        Solution bestSolution = new Solution( targetValue );
        for( final Set<Item> items : Sets.powerSet( ImmutableSet.copyOf( problem.getItems() ) ) ) {
            final Solution candidateSolution = new Solution( targetValue );
            for( final Item item : items ) {
                candidateSolution.add( item );
            }
            if( ! candidateSolution.isFeasible() ) {
                continue;
            }
            if( candidateSolution.isOptimal() ) {
                return candidateSolution;
            }
            if( candidateSolution.isBetterThan( bestSolution ) ) {
                bestSolution = candidateSolution;
            }
        }
        return bestSolution;
    }
}
