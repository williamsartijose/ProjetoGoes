package br.com.neolog.interview.user;

class UserAlreadyExistsException
    extends
        RuntimeException
{
    private static final long serialVersionUID = 1L;

    public UserAlreadyExistsException()
    {
        super( "user already exists" );
    }
}
