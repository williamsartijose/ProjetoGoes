package br.com.neolog.interview.optimization;

public interface Solver
{
    /**
     * Retorna um subconjunto dos itens do problema que se aproxima, mas nunca
     * excede o limite especificado.
     * 
     * @param problem problema de maximização
     * @return solução do problema, nunca é <code>null</code>
     * @throws NullPointerException se {@code problem} for <code>null</code>
     */
    Solution solve(
        Problem problem );
}