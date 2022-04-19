package br.com.neolog.interview.category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table( name = "product_category" )
public class ProductCategory
{
    @Id
    @SequenceGenerator( name = "product_category_id_seq", sequenceName = "product_category_id_seq", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "product_category_id_seq" )
    private Long id;

    @Size( min = 1, max = 255 )
    @NotEmpty
    @Column( name = "name" )
    private String name;

    protected ProductCategory()
    {
    }

    public ProductCategory(
        final String name )
    {
        this.name = name;
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

    public String getName()
    {
        return name;
    }

    public void setName(
        final String name )
    {
        this.name = name;
    }

}
