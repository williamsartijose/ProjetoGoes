package br.com.neolog.interview.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class UserServiceImpl
    implements
        UserService
{

    @Autowired
    private UserRepository userRespository;

    private final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    @Transactional
    public User save(
        final User user )
    {
        if( userRespository.existsByUsernameAndActiveTrue( user.getUsername() ) ) {
            throw new UserAlreadyExistsException();
        }
        final String password = user.getPassword();
        user.setPassword( encoder.encode( password ) );
        return userRespository.save( user );
    }

    @Override
    @Transactional
    public void update(
        final User user,
        final Long userId )
    {
        user.setId( userId );
        if( ! userRespository.existsByIdAndActiveTrue( user.getId() ) ) {
            throw new UserNotFoundException( user.getId() );
        }
        if( userRespository.existsByUsernameAndIdNotAndActiveTrue( user.getUsername(), user.getId() ) ) {
            throw new UserAlreadyExistsException();
        }

        userRespository.save( user );
    }

    @Override
    public List<User> getAll()
    {
        return userRespository.findAllByActiveTrue();
    }

    @Override
    public User findById(
        final Long userId )
    {
        return userRespository.findByIdAndActiveTrue( userId ).orElseThrow(
            () -> new UserNotFoundException( userId ) );
    }

    @Override
    @Transactional
    public void inactive(
        final Long userId )
    {
        final Optional<User> user = userRespository.findByIdAndActiveTrue( userId );
        final User heldUser = user.orElseThrow( () -> new UserNotFoundException( userId ) );
        heldUser.setActive( false );
        userRespository.save( heldUser );
    }

    @Override
    public Optional<User> findByUsername(
        final String username )
    {
        return userRespository.findByUsername( username );
    }

}
