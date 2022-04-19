package br.com.neolog.interview;

import static com.jayway.restassured.RestAssured.given;
import static java.util.Objects.requireNonNull;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.specification.RequestSpecification;

@SpringBootTest( classes = Main.class, webEnvironment = WebEnvironment.DEFINED_PORT )
@TestPropertySource( locations = {
    "classpath:/application.properties"
}, properties = {
    "spring.datasource.url=jdbc:h2:mem:testdatabase;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
    "server.port=9999",
    "spring.liquibase.contexts=production,integrated-test"
} )
@RunWith( SpringRunner.class )
public abstract class IntegratedTest
{
    private final String path;

    public IntegratedTest(
        final String path )
    {
        this.path = requireNonNull( path );
    }

    public RequestSpecification authenticatedRequest()
    {
        //@formatter:off
        return given().basePath( path ).port( 9999 )
            .header( HttpHeaders.AUTHORIZATION,"1234" )
            .log().all();
        //@formatter:on
    }
}
