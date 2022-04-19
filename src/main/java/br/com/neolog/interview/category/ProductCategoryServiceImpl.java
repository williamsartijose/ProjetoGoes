package br.com.neolog.interview.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class ProductCategoryServiceImpl
    implements
        ProductCategoryService
{
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory findById(
        final Long productCategoryId )
    {
        return productCategoryRepository.findById( productCategoryId ).orElseThrow(
            () -> new ProductCategoryNotFoundException( productCategoryId.toString() ) );
    }

    @Override
    @Transactional
    public ProductCategory save(
        final ProductCategory productCategory )
    {
        return productCategoryRepository.save( productCategory );
    }

    @Override
    public List<ProductCategory> getAll()
    {
        return productCategoryRepository.findAll();
    }

    @Override
    @Transactional
    public void update(
        final ProductCategory productCategory,
        final Long productCategoryId )
    {
        productCategory.setId( productCategoryId );
        if( ! productCategoryRepository.existsById( productCategory.getId() ) ) {
            throw new ProductCategoryNotFoundException( productCategory.getId().toString() );
        }
        productCategoryRepository.save( productCategory );
    }

    @Override
    @Transactional
    public void delete(
        final Long productCategoryId )
    {
        if( ! productCategoryRepository.existsById( productCategoryId ) ) {
            throw new ProductCategoryNotFoundException( productCategoryId.toString() );
        }
        productCategoryRepository.deleteById( productCategoryId );
    }
}
