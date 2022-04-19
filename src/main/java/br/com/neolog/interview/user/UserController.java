package br.com.neolog.interview.user;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.neolog.interview.handler.SuccessResponseBody;

@RestController
@RequestMapping( "/v1/users" )
class UserController
{

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthenticationService authentication;

    @RequestMapping( method = RequestMethod.POST )
    ResponseEntity<?> create(
        @RequestBody
        @Valid
        final User user )
    {
        final User createdUser = userService.save( user );

        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
            "/{id}" ).buildAndExpand( createdUser.getId() ).toUri();
        return ResponseEntity.created( location ).build();
    }

    @RequestMapping( path = "/{userId}", method = RequestMethod.PUT )
    ResponseEntity<?> edit(
        @RequestBody
        @Valid
        final User user,
        @PathVariable
        final Long userId )
    {
        userService.update( user, userId );

        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.noContent().location( location ).build();
    }

    @RequestMapping( method = RequestMethod.GET )
    SuccessResponseBody getAll()
    {
        return new SuccessResponseBody( userService.getAll() );
    }

    @RequestMapping( path = "/{userId}", method = RequestMethod.GET )
    SuccessResponseBody get(
        @PathVariable
        final Long userId )
    {
        return new SuccessResponseBody( userService.findById( userId ) );
    }

    @RequestMapping( path = "/{userId}", method = RequestMethod.DELETE )
    ResponseEntity<?> inactive(
        @PathVariable
        final Long userId )
    {
        userService.inactive( userId );

        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
            "/../" ).build().toUri();
        return ResponseEntity.noContent().location( location ).build();
    }

    @PostMapping( "/login" )
    String login(
        @RequestBody
        final User user )
    {
        return authentication.login( user.getUsername(), user.getPassword() ).orElseThrow(
            () -> new InvalidCredentialsException( "invalid login and/or password" ) );
    }

    @GetMapping( "/current" )
    SuccessResponseBody getCurrent(
        @AuthenticationPrincipal
        final User user )
    {
        return new SuccessResponseBody( user );
    }

    @GetMapping( "/logout" )
    ResponseEntity<?> logout(
        @AuthenticationPrincipal
        final User user )
    {
        authentication.logout( user );
        return ResponseEntity.ok().build();
    }

}
