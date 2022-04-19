package br.com.neolog.interview.handler;

public class SuccessResponseBody
{
    private final Object data;

    public SuccessResponseBody(
        final Object data )
    {
        this.data = data;
    }

    public Object getData()
    {
        return data;
    }

}
