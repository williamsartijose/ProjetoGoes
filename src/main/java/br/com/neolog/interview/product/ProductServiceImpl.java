package br.com.neolog.interview.product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.neolog.interview.category.ProductCategoryNotFoundException;
import br.com.neolog.interview.category.ProductCategoryRepository;

@Service
class ProductServiceImpl
    implements
        ProductService
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public Product findById(
        final Long productId )
    {
        final Optional<Product> product = productRepository.findById( productId );
        return product.orElseThrow( () -> new ProductNotFoundException( productId ) );
    }

    @Override
    public Page<Product> getAll(
        final int page,
        final int size )
    {
        return productRepository.findAll( PageRequest.of( page, size ) );
    }

    @Override
    @Transactional
    public Product save(
        final Product product )
    {
        final Long id = product.getProductCategory().getId();
        if( ! productCategoryRepository.existsById( id ) ) {
            throw new ProductCategoryNotFoundException( id.toString() );
        }
        return productRepository.save( product );
    }

    @Override
    @Transactional
    public Product update(
        final Product product,
        final Long productId )
    {
        product.setId( productId );
        if( ! productRepository.existsById( product.getId() ) ) {
            throw new ProductNotFoundException( product.getId().toString() );
        }
        final Long id = product.getProductCategory().getId();
        if( ! productCategoryRepository.existsById( id ) ) {
            throw new ProductCategoryNotFoundException( id.toString() );
        }

        return productRepository.save( product );
    }

    @Override
    @Transactional
    public void delete(
        final Long productId )
    {
        if( ! productRepository.existsById( productId ) ) {
            throw new ProductNotFoundException( productId.toString() );
        }
        productRepository.deleteById( productId );
    }

}
