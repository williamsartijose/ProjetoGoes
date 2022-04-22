package br.com.neolog.interview.suggestion;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/v1/suggestions" )
class SuggestionController
{
    @Autowired
    private SuggestionService suggestionService;

    @RequestMapping( method = RequestMethod.POST )
    public SuggestionResult suggest(@RequestBody @Valid final SuggestionParamaters criteria )
    {
        final List<SuggestedItem> suggestion = suggestionService.produce( criteria );
        return new SuggestionResult( suggestion );
    }
//sugetion
    @RequestMapping( method = RequestMethod.POST, value = "/sugetionWeight" )
    SuggestionResult suggestWeight(
        @RequestBody
        @Valid
        final SuggestionParamaters criteria )
    {
        final List<SuggestedItem> suggestion = suggestionService.sugestionWeight( criteria );
        return new SuggestionResult( suggestion );


    }

    @RequestMapping( method = RequestMethod.POST, value = "/sugestionVolume" )
    SuggestionResult sugestionVolume(
            @RequestBody
            @Valid
            final SuggestionParamaters criteria )
    {
        final List<SuggestedItem> suggestion = suggestionService.sugestionVolume( criteria );
        return new SuggestionResult( suggestion );


    }
}
