package br.com.neolog.interview.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
    extends
        JpaRepository<User,Long>
{

    boolean existsByUsernameAndActiveTrue(
        String username );

    boolean existsByUsernameAndIdNotAndActiveTrue(
        String username,
        Long idNot );

    Optional<User> findByIdAndActiveTrue(
        Long id );

    boolean existsByIdAndActiveTrue(
        Long id );

    List<User> findAllByActiveTrue();

    Optional<User> findByUsername(
        String username );

    Optional<User> findByUsernameAndPassword(
        String username,
        String password );

    Optional<User> findByToken(
        String token );

}
