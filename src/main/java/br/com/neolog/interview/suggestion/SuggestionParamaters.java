package br.com.neolog.interview.suggestion;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonCreator;

final class SuggestionParamaters
{
    private final Integer targetPrice;

    @JsonCreator
    SuggestionParamaters(
        @Positive
        @NotNull
        final Integer targetPrice )
    {
        this.targetPrice = targetPrice;
    }

    public Integer getTargetPrice()
    {
        return targetPrice;
    }
}
