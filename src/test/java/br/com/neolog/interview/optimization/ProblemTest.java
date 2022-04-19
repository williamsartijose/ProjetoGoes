package br.com.neolog.interview.optimization;

import static org.assertj.core.api.Assertions.shouldHaveThrown;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import br.com.neolog.interview.optimization.Item;
import br.com.neolog.interview.optimization.Problem;

public class ProblemTest
{
    @Test( expected = NullPointerException.class )
    public void shouldThrowNullPointerExceptionWhenItemListIsNull()
    {
        new Problem( 10, null );
        shouldHaveThrown( NullPointerException.class );
    }

    @Test( expected = IllegalArgumentException.class )
    public void shouldThrowIllegalArgumentExceptionWhenTargetValueIsLessThanOne()
    {
        new Problem( 0, Arrays.asList( new Item( 10, 1 ) ) );
        shouldHaveThrown( IllegalArgumentException.class );
    }

    @Test( expected = IllegalArgumentException.class )
    public void shouldThrowIllegalArgumentExceptionWhenItemListIsEmpty()
    {
        new Problem( 10, Collections.emptyList() );
        shouldHaveThrown( IllegalArgumentException.class );
    }
}
