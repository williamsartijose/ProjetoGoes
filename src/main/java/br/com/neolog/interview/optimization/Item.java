package br.com.neolog.interview.optimization;

import static com.google.common.base.MoreObjects.toStringHelper;

public final class Item
    implements
        Comparable<Item>
{
    private final long value;
    private final long identifier;

    public Item(
        final long value,
        final long identifier )
    {
        this.value = value;
        this.identifier = identifier;
    }

    public long getValue()
    {
        return value;
    }
    
    public long getIdentifier()
    {
        return identifier;
    }

    @Override
    public int compareTo(
        final Item o )
    {
        return Long.compare( getValue(), o.getValue() );
    }

    @Override
    public String toString()
    {
        return toStringHelper( this )
            .add( "value", value )
            .add( "identifier", identifier )
            .toString();
    }
}