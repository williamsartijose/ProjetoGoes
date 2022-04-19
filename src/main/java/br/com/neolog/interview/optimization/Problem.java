package br.com.neolog.interview.optimization;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class Problem
{
    private final List<Item> items;
    private final int targetValue;

    public Problem(
        final int targetValue,
        final List<Item> items )
    {
        checkArgument( targetValue > 0, "Target Value was %s, but expected number greater than %s.",
            targetValue, 0 );
        checkNotNull( items, "Argument items cannot be null." );
        checkArgument( items.size() > 0, "Argument items cannot be empty." );

        this.targetValue = targetValue;
        this.items = ImmutableList.copyOf( items );
    }

    public List<Item> getItems()
    {
        return items;
    }

    public int getTargetValue()
    {
        return targetValue;
    }
}
