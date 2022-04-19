package br.com.neolog.interview.stockitem;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.neolog.interview.product.Product;
import br.com.neolog.interview.product.ProductNotFoundException;
import br.com.neolog.interview.product.ProductRepository;

@Service
class StockItemServiceImpl
    implements
        StockItemService
{
    @Autowired
    private StockItemRepository stockItemRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public void manageStock(
        final StockItem stockItemNoDefProduct,
        final Long productId )
    {
        final Product product = productRepository.findById( productId ).orElseThrow(
            ProductNotFoundException::new );
        stockItemNoDefProduct.setProduct( product );

        final Optional<StockItem> stockItemFound = stockItemRepository.findByProductId(
            stockItemNoDefProduct.getProduct().getId() );
        if( ! stockItemFound.isPresent() ) {
            stockItemRepository.save( stockItemNoDefProduct );
        } else {
            final StockItem heldStockItem = stockItemFound.get();
            heldStockItem.addToStock( stockItemNoDefProduct.getStock() );
        }
    }

    @Override
    public Optional<StockItem> findByProductId(
        final Long productId )
    {
        return stockItemRepository.findByProductId( productId );
    }

    @Override
    public StockItem findByProductIdOrZeroQuantity(
        final Long productId )
    {
        final Optional<StockItem> stockItem = stockItemRepository.findByProductId( productId );
        if( stockItem.isPresent() ) {
            return stockItem.get();
        }

        final Optional<Product> product = productRepository.findById( productId );
        final Product heldProduct = product.orElseThrow(
            () -> new ProductNotFoundException( productId ) );

        return StockItem.zeroQuantity( heldProduct );
    }
}
