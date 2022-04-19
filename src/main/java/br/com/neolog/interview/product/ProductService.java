package br.com.neolog.interview.product;

import org.springframework.data.domain.Page;

public interface ProductService
{

    Product findById(
        Long productId );

    Page<Product> getAll(
        int page,
        int size );

    Product save(
        Product product );

    Product update(
        Product product,
        Long productId );

    void delete(
        Long productId );

}