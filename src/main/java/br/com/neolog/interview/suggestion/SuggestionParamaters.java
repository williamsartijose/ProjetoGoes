package br.com.neolog.interview.suggestion;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonCreator;

final class SuggestionParamaters
{
    private final Integer suggestionParamater;

    @JsonCreator
    SuggestionParamaters(
        @Positive
        @NotNull
        final Integer suggestionParamater )
    {

        this.suggestionParamater = suggestionParamater;

    }

    public Integer getSuggestionParamater()
    {
        return suggestionParamater;
    }

}
