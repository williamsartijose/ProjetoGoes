package br.com.neolog.interview.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository
    extends
        JpaRepository<ProductCategory,Long>
{

}
