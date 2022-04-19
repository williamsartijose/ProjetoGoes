package br.com.neolog.interview.stockitem;

public class InsufficientStockException
    extends
        RuntimeException
{

    public InsufficientStockException(
        final Long quantity )
    {
        super(
            String.format( "could not add order item, insufficient stock (quantity=%s)",
                quantity ) );
    }

    private static final long serialVersionUID = 1L;

}
