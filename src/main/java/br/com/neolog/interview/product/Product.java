package br.com.neolog.interview.product;

import static com.google.common.base.MoreObjects.toStringHelper;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.neolog.interview.category.ProductCategory;

@Entity
@Table( name = "product" )
public class Product
{
    @Id
    @SequenceGenerator( name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "product_id_seq" )
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Long weight;
    private Long volume;
    @ManyToOne
    private ProductCategory productCategory;

    @JsonCreator
    protected Product()
    {
    }

    public Product(
        final String name,
        final String description,
        final long price,
        final long weight,
        final long volume,
        final ProductCategory productCategory )
    {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.volume = volume;
        this.productCategory = productCategory;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(
        final Long id )
    {
        this.id = id;
    }

    @NotEmpty
    @Size( min = 1, max = 255 )
    public String getName()
    {
        return name;
    }

    public void setName(
        final String name )
    {
        this.name = name;
    }

    @Size( min = 1, max = 255 )
    public String getDescription()
    {
        return description;
    }

    public void setDescription(
        final String description )
    {
        this.description = description;
    }

    @Positive
    @NotNull
    public Long getPrice()
    {
        return price;
    }

    public void setPrice(
        final Long price )
    {
        this.price = price;
    }

    @Positive
    @NotNull
    public Long getVolume()
    {
        return volume;
    }

    @Positive
    @NotNull
    public Long getWeight()
    {
        return weight;
    }

    @NotNull
    public ProductCategory getProductCategory()
    {
        return productCategory;
    }

    public void setProductCategory(
        final ProductCategory productCategory )
    {
        this.productCategory = productCategory;
    }

    @Override
    public String toString()
    {
        return toStringHelper( this ).add( "id", id ).add( "name", name ).add( "description",
            description ).add( "price", price ).add( "productCategory", productCategory ).add(
                "volume", volume ).add( "weight", weight ).toString();
    }
}
