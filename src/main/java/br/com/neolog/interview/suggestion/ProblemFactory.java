package br.com.neolog.interview.suggestion;

import static java.util.Objects.requireNonNull;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.interview.optimization.Item;
import br.com.neolog.interview.optimization.Problem;
import br.com.neolog.interview.product.Product;
import br.com.neolog.interview.stockitem.StockItem;
import br.com.neolog.interview.stockitem.StockItemRepository;

@Component
class ProblemFactory
{
    @Autowired
    private StockItemRepository stockItemRepository;

    public Problem create(
        final SuggestionParamaters criteria )
    {
        final Integer targetPrice = requireNonNull( criteria.getTargetPrice() );
        final List<Item> items = generateItems( targetPrice );
        return new Problem( targetPrice, items );
    }

    private List<Item> generateItems(
        final Integer targetPrice )
    {
        final List<StockItem> stockItems = stockItemRepository.findByStockGreaterThanAndProductPriceLessThan(
            0, targetPrice.longValue() + 1 );
        final List<Item> items = new LinkedList<>();
        for( final StockItem stockItem : stockItems ) {
            items.addAll( convertStockItemToItems( stockItem, targetPrice ) );
        }
        return items;
    }

    /**
     * Quebra um {@link StockItem} em diversos {@link Item}s. Cada instância de
     * {@link Item} equivale a uma quantia de estoque igual a 1. Entretanto, a
     * quantidade final de {@link Item}s respeita também o limite de preço
     * especificado neste método, evitando a criação de instância desnecessárias
     * de {@link Item}.
     */
    private static List<Item> convertStockItemToItems(
        final StockItem stockItem,
        final Integer targetPrice )
    {
        final List<Item> items = new LinkedList<>();
        final Product product = stockItem.getProduct();
        final long price = product.getPrice().longValue();
        final long maxQuantity = Math.min( targetPrice / price, stockItem.getStock().longValue() );
        for( int quantity = 0; quantity < maxQuantity; quantity++ ) {
            items.add( new Item( price, product.getId() ) );
        }
        return items;
    }
}
