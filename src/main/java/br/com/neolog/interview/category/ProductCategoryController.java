package br.com.neolog.interview.category;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.neolog.interview.handler.SuccessResponseBody;

@RestController
@RequestMapping( "/v1/categories" )
class ProductCategoryController
{
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping( path = "/{productCategoryId}", method = RequestMethod.GET )
    SuccessResponseBody get(
        @PathVariable
        final Long productCategoryId )
    {
        return new SuccessResponseBody( productCategoryService.findById( productCategoryId ) );
    }

    @RequestMapping( method = RequestMethod.GET )
    SuccessResponseBody getAll()
    {
        return new SuccessResponseBody( productCategoryService.getAll() );
    }

    @RequestMapping( method = RequestMethod.POST )
    ResponseEntity<?> create(
        @RequestBody
        @Valid
        final ProductCategory productCategory )
    {
        final ProductCategory savedCategoryProduct = productCategoryService.save( productCategory );
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
            "/{id}" ).buildAndExpand( savedCategoryProduct.getId() ).toUri();
        return ResponseEntity.created( location ).build();
    }

    @RequestMapping( path = "/{productCategoryId}", method = RequestMethod.PUT )
    ResponseEntity<?> edit(
        @PathVariable
        final Long productCategoryId,
        @RequestBody
        @Valid
        final ProductCategory productCategory )
    {
        productCategoryService.update( productCategory, productCategoryId );
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.noContent().location( location ).build();
    }
}
