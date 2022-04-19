package br.com.neolog.interview.suggestion;

import java.util.List;

interface SuggestionService
{

    List<SuggestedItem> produce(
        SuggestionParamaters criteria );

}
