package br.com.neolog.interview.stockitem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.google.common.base.MoreObjects;

import br.com.neolog.interview.product.Product;

@Entity
@Table( name = "stock_item" )
public class StockItem
{
    @Id
    @SequenceGenerator( name = "stock_item_id_seq", sequenceName = "stock_item_id_seq", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "stock_item_id_seq" )
    private Long id;

    @ManyToOne
    private Product product;

    private Long stock;

    public static StockItem zeroQuantity(
        final Product product )
    {
        final StockItem stockItem = new StockItem();
        stockItem.setProduct( product );
        stockItem.setStock( 0L );
        return stockItem;
    }

    StockItem()
    {
    }

    @NotNull
    public Product getProduct()
    {
        return product;
    }

    public void setProduct(
        final Product product )
    {
        this.product = product;
    }

    @Min( 0 )
    @Max( Long.MAX_VALUE )
    @NotNull
    public Long getStock()
    {
        return stock;
    }

    public void setStock(
        final Long stock )
    {
        this.stock = stock;
    }

    public boolean hasAvailable(
        @Positive
        final long quantity )
    {
        return stock >= quantity;
    }

    public void removeStock(
        final long quantity )
    {
        stock -= quantity;
    }

    public void addToStock(
        final long quantity )
    {
        final long futureStock = stock.longValue() + quantity;
        if( futureStock < 0 ) {
            throw new InvalidStockException( "could not set negative stock" );
        }
        this.stock = Long.valueOf( futureStock );
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this ).add( "inStock", stock ).add( "product",
            product ).toString();
    }
}
