package br.com.neolog.interview.suggestion;

import br.com.neolog.interview.product.Product;

class SuggestedItem
{
    private final Product product;
    private final long quantity;

    private SuggestedItem(
        final Product product,
        final long quantity )
    {
        this.product = product;
        this.quantity = quantity;

    }

    public static SuggestedItem withProductAndQuantity(
        final Product product,
        final long quantity )
    {
        return new SuggestedItem( product, quantity );
    }

    public Product getProduct()
    {
        return product;
    }

    public long getQuantity()
    {
        return quantity;
    }

}
