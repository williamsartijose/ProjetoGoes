package br.com.neolog.interview.product;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.neolog.interview.handler.SuccessResponseBody;

@RestController
@RequestMapping( "/v1/products" )
class ProductController
{
    @Autowired
    private ProductService productService;

    @RequestMapping( path = "/{productId}", method = RequestMethod.GET )
    SuccessResponseBody get(
        @PathVariable
        final Long productId )
    {
        return new SuccessResponseBody( productService.findById( productId ) );
    }

    @RequestMapping( method = RequestMethod.GET )
    SuccessResponseBody getAll(
        @RequestParam( name = "page", required = false, defaultValue = "0" )
        final Integer page,
        @RequestParam( name = "size", required = false, defaultValue = "10" )
        final Integer pageSize )
    {
        return new SuccessResponseBody(
            productService.getAll( page.intValue(), pageSize.intValue() ) );
    }

    @RequestMapping( method = RequestMethod.POST )
    ResponseEntity<?> create(
        @RequestBody
        @Valid
        final Product product )
    {
        final Product savedProduct = productService.save( product );
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
            "/{id}" ).buildAndExpand( savedProduct.getId() ).toUri();
        return ResponseEntity.created( location ).build();
    }

    @RequestMapping( path = "/{productId}", method = RequestMethod.PUT )
    ResponseEntity<?> edit(
        @PathVariable
        final Long productId,
        @RequestBody
        @Valid
        final Product product )
    {
        productService.update( product, productId );
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.noContent().location( location ).build();
    }

}
