package br.com.neolog.interview.user;

import java.util.List;
import java.util.Optional;

interface UserService
{

    User save(
        User user );

    void update(
        User user,
        Long userId );

    List<User> getAll();

    User findById(
        Long userId );

    void inactive(
        Long userId );

    Optional<User> findByUsername(
        String username );

}
