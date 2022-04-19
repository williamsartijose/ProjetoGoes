package br.com.neolog.interview.optimization;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
class SolverConfiguration
{
    @Bean
    @Primary
    public Solver solver(
        @Qualifier("bruteForceSolver")
        final Solver bruteForceSolver,
        @Qualifier( "heuristicSolver" )
        final Solver heuristicSolver )
    {
        return new RouterSolver( bruteForceSolver, heuristicSolver );
    }

    @Bean
    public Solver bruteForceSolver()
    {
        return new BruteForceSolver();
    }

    @Bean
    public Solver heuristicSolver()
    {
        return new GreedyHeuristicSolver();
    }
}
