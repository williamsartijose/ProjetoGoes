package br.com.neolog.interview.suggestion;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

public final class SuggestionResult
{
    private final List<SuggestedItem> data;

    @JsonCreator
    public SuggestionResult(
        final List<SuggestedItem> data )
    {
        this.data = data;
    }

    public List<SuggestedItem> getData()
    {
        return data;
    }
}
