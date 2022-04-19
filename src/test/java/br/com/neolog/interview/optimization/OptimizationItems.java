package br.com.neolog.interview.optimization;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Longs;

import br.com.neolog.interview.optimization.Item;

public abstract class OptimizationItems
{
    private OptimizationItems()
    {
    }

    static List<Item> generateItems(
        final long... values )
    {
        return generateItems( Longs.asList( values ) );
    }

    static List<Item> generateItems(
        final Iterable<Long> values )
    {
        final ImmutableList.Builder<Item> builder = ImmutableList.builder();
        long identifier = 1L;
        for( final long value : values ) {
            builder.add( new Item( value, identifier++ ) );
        }
        return builder.build();
    }

}
