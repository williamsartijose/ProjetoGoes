package br.com.neolog.interview.stockitem;

import java.net.URI;

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
@RequestMapping( "/v1/products/{productId}/stock" )
class StockItemController
{

    @Autowired
    private StockItemService stockItemService;

    @RequestMapping( method = RequestMethod.POST )
    ResponseEntity<?> manage(
        @PathVariable
        final Long productId,
        @RequestBody
        final StockItem stockItem )
    {
        stockItemService.manageStock( stockItem, productId );

        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.noContent().location( location ).build();
    }

    @RequestMapping( method = RequestMethod.GET )
    SuccessResponseBody stockByProductId(
        @PathVariable
        final Long productId )
    {
        return new SuccessResponseBody(
            stockItemService.findByProductIdOrZeroQuantity( productId ) );
    }

}
