package br.com.neolog.interview.user;

import java.util.Base64;
import java.util.Optional;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class UserAuthenticationServiceImpl
    implements
        UserAuthenticationService
{
    @Autowired
    private UserRepository userRespository;
    @Autowired
    private UserService userService;

    private final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    @Transactional
    public Optional<String> login(
        final String username,
        final String password )
    {
        final Optional<User> user = userService.findByUsername( username );
        if( ! user.isPresent() ) {
            return Optional.empty();
        }
        final User foundUser = user.get();
        if( ! encoder.matches( password, foundUser.getPassword() ) ) {
            return Optional.empty();
        }
        final LocalDateTime tokenExpiration = LocalDateTime.now().plusHours( 2 );
        final String src = String.format( "%s:%s:%s:%s", username, tokenExpiration,
            foundUser.getPassword(), "mySecretKey" );
        final String token = Base64.getEncoder().encodeToString( src.getBytes() ).substring( 0,
            64 );
        foundUser.setTokenExpiration( tokenExpiration );
        foundUser.setToken( token );
        return Optional.of( token );
    }

    @Override
    public Optional<User> findByToken(
        final String token )
    {
        final Optional<User> user = userRespository.findByToken( token );
        if( ! user.isPresent() ) {
            return Optional.empty();
        }

        final User foundUser = user.get();
        final LocalDateTime tokenExpiration = foundUser.getTokenExpiration();
        if( tokenExpiration.isBefore( LocalDateTime.now() ) ) {
            return Optional.empty();
        }

        return user;
    }

    @Override
    @Transactional
    public void logout(
        final User user )
    {
        user.setToken( null );
        user.setTokenExpiration( null );
        userRespository.save( user );
    }
}
