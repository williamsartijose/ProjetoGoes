package br.com.neolog.interview.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
    extends
        JpaRepository<Product,Long>
{
}
