package br.com.neolog.interview.suggestion;

import java.util.List;

interface SuggestionService
{

    List<SuggestedItem> produce(
        SuggestionParamaters criteria );

    // crio um novo metodo que a classe java q implentar essa interface tera que
    // ter
    List<SuggestedItem> sugestionWeight(
        SuggestionParamaters criteria );

    List<SuggestedItem> sugestionVolume(
            SuggestionParamaters criteria );

}
