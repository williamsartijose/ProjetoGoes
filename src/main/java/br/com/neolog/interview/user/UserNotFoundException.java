package br.com.neolog.interview.user;

class UserNotFoundException
    extends
        RuntimeException
{
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(
        final Long id )
    {
        super( String.format( "user #%s not found", id ) );
    }
}
