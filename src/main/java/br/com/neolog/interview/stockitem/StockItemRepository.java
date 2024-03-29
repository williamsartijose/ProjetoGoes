package br.com.neolog.interview.stockitem;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockItemRepository
    extends
        JpaRepository<StockItem,Long>
{

    Optional<StockItem> findByProductId(
        Long productId );

    boolean existsByProductIdAndStockGreaterThanEqual(
        Long productId,
        Long quantity );

    @Override
    List<StockItem> findAll();

    List<StockItem> findByProductPriceLessThan(
        long price );

    List<StockItem> findByStockGreaterThanAndProductPriceLessThan(
        long stock,
        long price );

    // find by
    List<StockItem> findByStockGreaterThanAndProductWeightLessThan(
        long stock,
        long weigth );

    List<StockItem> findByStockGreaterThanAndProductVolumeLessThan(
        long stock,
        long volume );
}
