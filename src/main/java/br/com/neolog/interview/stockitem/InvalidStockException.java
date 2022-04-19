package br.com.neolog.interview.stockitem;

class InvalidStockException
    extends
        RuntimeException
{

    public InvalidStockException(
        final String message )
    {
        super( message );
    }

    private static final long serialVersionUID = 1L;

}
