package br.com.neolog.interview.suggestion;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonCreator;

final class SuggestionParamaters
{
    private final Integer targetPrice;
    private final Integer maximumVolume;
    private final Integer maximumWeight;

    @JsonCreator
    SuggestionParamaters(
        @Positive
        @NotNull
        final Integer targetPrice,
        final Integer maximumVolume,
        final Integer maximumWeight )
    {

        this.targetPrice = targetPrice;
        this.maximumVolume = maximumVolume;
        this.maximumWeight = maximumWeight;
    }

    public Integer getTargetPrice()
    {
        return targetPrice;
    }

    public Integer getMaximumVolume()
    {
        return maximumVolume;
    }

    public Integer getMaximumWeight()
    {
        return maximumWeight;
    }

}
