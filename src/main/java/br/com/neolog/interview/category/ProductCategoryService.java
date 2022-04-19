package br.com.neolog.interview.category;

import java.util.List;

interface ProductCategoryService
{

    ProductCategory findById(
        Long productCategoryId );

    ProductCategory save(
        ProductCategory productCategory );

    List<ProductCategory> getAll();

    void update(
        ProductCategory productCategory,
        Long productCategoryId );

    void delete(
        Long productCategoryId );

}
