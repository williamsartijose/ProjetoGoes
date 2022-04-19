package br.com.neolog.interview.optimization;

import static java.util.Objects.requireNonNull;

public final class RouterSolver
    implements
        Solver
{
    private static final int MAX_FOR_BRUTE_FORCE = 20;

    private final Solver upToThresholdDelegate;
    private final Solver greaterThanThresholdDelegate;

    /**
     * @param lessThanLimitDelegate solver a delegar caso o problema contenha
     *        até {@value #MAX_FOR_BRUTE_FORCE} inclusive
     * @param greaterThanLimitDelegate solver a delegar caso o problema contenha
     *        mais de {@value #MAX_FOR_BRUTE_FORCE}
     * @throws NullPointerException caso algum dos solvers a delegar sejam
     *         <code>null</code>
     */
    RouterSolver(
        final Solver lessThanLimitDelegate,
        final Solver greaterThanLimitDelegate )
    {
        this.upToThresholdDelegate = requireNonNull( lessThanLimitDelegate );
        this.greaterThanThresholdDelegate = requireNonNull( greaterThanLimitDelegate );
    }

    @Override
    public Solution solve(
        final Problem problem )
    {
        if( problem.getItems().size() <= MAX_FOR_BRUTE_FORCE ) {
            return upToThresholdDelegate.solve( problem );
        }
        return greaterThanThresholdDelegate.solve( problem );
    }
}
