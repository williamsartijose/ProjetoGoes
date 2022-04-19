package br.com.neolog.interview.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( HttpStatus.NOT_FOUND )
public class ProductNotFoundException
    extends
        RuntimeException
{
    private static final long serialVersionUID = 1L;

    public ProductNotFoundException()
    {
    }

    public ProductNotFoundException(
        final String productId )
    {
        super( String.format( "product #%s not found", productId ) );
    }

    public ProductNotFoundException(
        final Long productId )
    {
        super( String.format( "product #%s not found", productId ) );
    }

}
