package br.com.neolog.interview.stockitem;

import java.util.Optional;

interface StockItemService
{
    void manageStock(
        StockItem stockItemNoDefProduct,
        Long productId );

    Optional<StockItem> findByProductId(
        Long productId );

    StockItem findByProductIdOrZeroQuantity(
        Long productId );
}
