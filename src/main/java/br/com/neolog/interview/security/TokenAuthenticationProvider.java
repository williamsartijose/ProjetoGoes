package br.com.neolog.interview.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.neolog.interview.user.UserAuthenticationService;

@Component
final class TokenAuthenticationProvider
    extends
        AbstractUserDetailsAuthenticationProvider
{
    @Autowired
    UserAuthenticationService auth;

    @Override
    protected void additionalAuthenticationChecks(
        final UserDetails d,
        final UsernamePasswordAuthenticationToken auth )
    {
        // Nothing to do
    }

    @Override
    protected UserDetails retrieveUser(
        final String username,
        final UsernamePasswordAuthenticationToken authentication )
    {
        final Object token = authentication.getCredentials();
        return Optional.ofNullable( token ).map( String::valueOf )
            .flatMap( auth::findByToken )
            .orElseThrow( () -> new UsernameNotFoundException( "Cannot find user with authentication token=" + token ) );
    }
}
