package br.com.neolog.interview.security;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static java.util.Optional.ofNullable;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

final class TokenAuthenticationFilter
    extends
        AbstractAuthenticationProcessingFilter
{
    TokenAuthenticationFilter(
        final RequestMatcher requiresAuth )
    {
        super( requiresAuth );
    }

    @Override
    public Authentication attemptAuthentication(
        final HttpServletRequest request,
        final HttpServletResponse response )
    {
        final String token = ofNullable( request.getHeader( AUTHORIZATION ) ).orElseThrow(
            () -> new BadCredentialsException( "Missing authentication" ) );
        final Authentication auth = new UsernamePasswordAuthenticationToken( token, token );
        return getAuthenticationManager().authenticate( auth );
    }

    @Override
    protected void successfulAuthentication(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final FilterChain chain,
        final Authentication authResult )
        throws IOException,
            ServletException
    {
        super.successfulAuthentication( request, response, chain, authResult );
        chain.doFilter( request, response );
    }
}
