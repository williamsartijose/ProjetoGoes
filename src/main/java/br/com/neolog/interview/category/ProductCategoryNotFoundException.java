package br.com.neolog.interview.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( HttpStatus.NOT_FOUND )
public class ProductCategoryNotFoundException
    extends
        RuntimeException
{
    private static final long serialVersionUID = 1L;

    public ProductCategoryNotFoundException(
        final String productCategoryId )
    {
        super( String.format( "product category #%s not found", productCategoryId ) );
    }
}
