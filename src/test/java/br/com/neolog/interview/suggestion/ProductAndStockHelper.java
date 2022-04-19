package br.com.neolog.interview.suggestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.interview.category.ProductCategory;
import br.com.neolog.interview.category.ProductCategoryRepository;
import br.com.neolog.interview.product.Product;
import br.com.neolog.interview.product.ProductRepository;
import br.com.neolog.interview.stockitem.StockItem;
import br.com.neolog.interview.stockitem.StockItemRepository;

@Component
class ProductAndStockHelper
{
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StockItemRepository stockItemRepository;

    private ProductCategory productCategory;

    void createProductAndStock(
        final int identifier,
        final long price,
        final long weight,
        final long volume,
        final long stock )
    {
        final String name = new StringBuilder( "product" ).append( identifier ).toString();
        final Product product = new Product(
            name,
            name,
            price,
            weight,
            volume,
            getProductCategory() );
        productRepository.save( product );
        final StockItem item = StockItem.zeroQuantity( product );
        item.setStock( stock );
        stockItemRepository.save( item );
    }

    public ProductCategory getProductCategory()
    {
        if( productCategory == null ) {
            productCategory = productCategoryRepository.findById( 1L ).get();
        }
        return productCategory;
    }
}