package br.com.neolog.interview.suggestion;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import br.com.neolog.interview.optimization.Item;
import br.com.neolog.interview.optimization.Problem;
import br.com.neolog.interview.optimization.Solution;
import br.com.neolog.interview.optimization.Solver;
import br.com.neolog.interview.product.Product;
import br.com.neolog.interview.product.ProductRepository;

@Service
class SuggestionServiceImpl
    implements
        SuggestionService
{
    @Autowired
    private Solver solver;
    @Autowired
    private ProblemFactory problemFactoryService;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<SuggestedItem> produce(@Valid final SuggestionParamaters criteria )
    {
        final Problem problem = problemFactoryService.create( criteria );
        final Solution solution = solver.solve( problem );
        final List<SuggestedItem> suggestions = convertItemsToSuggestions( solution.getItems() );
        return suggestions;
    }

    private List<SuggestedItem> convertItemsToSuggestions(
        final List<Item> items )
    {
        if( items.isEmpty() ) {
            return Collections.emptyList();
        }
        final Multiset<Long> productIds = HashMultiset.create();
        for( final Item item : items ) {
            productIds.add( item.getIdentifier() );
        }
        final List<Product> products = productRepository.findAllById( productIds );
        // @formatter:off
        return products.stream()
            .map(product -> SuggestedItem.withProductAndQuantity( product, productIds.count( product.getId() ) ) )
            .collect( toList() );
        // @formatter:on
    }

    // sobrescrita do metodo q interface obriga ter
    @Override
    public List<SuggestedItem> sugestionWeight(
        SuggestionParamaters criteria )
    {
        final Problem problem = problemFactoryService.sugestionWeight( criteria );
        final Solution solution = solver.solve( problem );//
        final List<SuggestedItem> suggestions = convertItemsToSuggestions( solution.getItems() );
        return suggestions;
    }

    @Override
    public List<SuggestedItem> sugestionVolume(SuggestionParamaters criteria) {
        final Problem problem = problemFactoryService.sugestionVolume( criteria );
        final Solution solution = solver.solve( problem );//
        final List<SuggestedItem> suggestions = convertItemsToSuggestions( solution.getItems() );
        return suggestions;
    }

}
