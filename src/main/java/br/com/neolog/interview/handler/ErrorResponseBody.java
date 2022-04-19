package br.com.neolog.interview.handler;

import java.util.List;

class ErrorResponseBody
{
    private final List<Error> errors;

    public ErrorResponseBody(
        final List<Error> errors )
    {
        this.errors = errors;
    }

    public List<Error> getErrors()
    {
        return errors;
    }

}
