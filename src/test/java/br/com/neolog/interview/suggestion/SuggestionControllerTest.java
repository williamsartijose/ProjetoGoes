package br.com.neolog.interview.suggestion;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import br.com.neolog.interview.IntegratedTest;

public class SuggestionControllerTest
    extends
        IntegratedTest
{
    @Autowired
    private ProductAndStockHelper productHelper;

    public SuggestionControllerTest()
    {
        super( "/v1/suggestions" );
    }

    @Test
    public void suggestionIntegratedTest()
    {
        productHelper.createProductAndStock( 1, 100, 100, 100, 100 );
        productHelper.createProductAndStock( 2, 40L, 40L, 40L, 100 );
        productHelper.createProductAndStock( 3, 50L, 50L, 50L, 100 );
        productHelper.createProductAndStock( 4, 80L, 80L, 80L, 100 );
        productHelper.createProductAndStock( 5, 200, 200, 200, 100 );

        //@formatter:off
        final Response post = authenticatedRequest().contentType( ContentType.JSON ).content( new SuggestionParamaters( 190, null , null ) ).post();
        post.then().log().all();
        final SuggestionResult result = post.as( SuggestionResult.class );
        assertThat( result.getData() ).hasSize( 3 )
            .allMatch( item -> item.getQuantity() == 1l )
            .extracting( item -> item.getProduct().getPrice() ).containsExactlyInAnyOrder( 100L, 40L, 50L );
        //@formatter:on
    }
}
