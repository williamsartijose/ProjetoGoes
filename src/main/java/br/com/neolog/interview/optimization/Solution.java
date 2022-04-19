package br.com.neolog.interview.optimization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Solution
{
    private final List<Item> items;
    private final int targetValue;
    private int reachedValue;

    public Solution(
        final int targetValue )
    {
        this.targetValue = targetValue;
        this.items = new ArrayList<>();
    }

    public int getReachedValue()
    {
        return reachedValue;
    }

    public List<Item> getItems()
    {
        return Collections.unmodifiableList( items );
    }

    public boolean fits(
        final Item item )
    {
        return reachedValue + item.getValue() <= targetValue;
    }

    public void add(
        final Item item )
    {
        reachedValue += item.getValue();
        items.add( item );
    }

    public boolean isOptimal()
    {
        return reachedValue == targetValue;
    }

    public boolean isFeasible()
    {
        return reachedValue <= targetValue;
    }

    public boolean isBetterThan(
        final Solution solution )
    {
        if( isFeasible() != solution.isFeasible() ) {
            return isFeasible();
        }
        return getReachedValue() > solution.getReachedValue();
    }
}