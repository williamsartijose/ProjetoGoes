package br.com.neolog.interview.stockitem;

public class StockItemNotFoundException
    extends
        RuntimeException
{

    private static final long serialVersionUID = 1L;

    public StockItemNotFoundException(
        final Long productId )
    {
        super( String.format( "stock item of the product #%s not found", productId ) );
    }
}
