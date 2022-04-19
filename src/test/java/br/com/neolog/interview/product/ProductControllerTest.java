package br.com.neolog.interview.product;

import static java.util.regex.Pattern.matches;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyString;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.jayway.restassured.http.ContentType;

import br.com.neolog.interview.IntegratedTest;
import br.com.neolog.interview.category.ProductCategory;
import br.com.neolog.interview.category.ProductCategoryRepository;

public class ProductControllerTest
    extends
        IntegratedTest
{
    public ProductControllerTest()
    {
        super( "/v1/products" );
    }

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void shouldApplyDefaultPageSizeWhenNotSpecifiedOtherwise()
    {
        //@formatter:off
        authenticatedRequest().when().get().then().log().all()
            .assertThat().body( "data.pageable.pageSize", equalTo( 10 ) );
        //@formatter:on
    }

    @Test
    public void shouldCreateProduct()
    {
        //@formatter:off
        final ProductCategory productCategory = productCategoryRepository.findById( 1L ).get();
        final Product product = new Product( "product1", "product1Description", 10L, 11L, 12L, productCategory );
        authenticatedRequest().when().contentType( ContentType.JSON ).content( product ).post().then()
            .log().all().and().assertThat()
                .statusCode( HttpStatus.CREATED.value() )
                .body( isEmptyString() )
                .header( HttpHeaders.LOCATION, header -> matches( ".*/v1/products/\\d+", header ), equalTo( Boolean.TRUE ) );
        //@formatter:on
    }
}
