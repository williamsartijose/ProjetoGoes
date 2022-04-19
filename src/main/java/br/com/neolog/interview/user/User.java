package br.com.neolog.interview.user;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table( name = "user_table" )
public class User
    implements
        UserDetails
{

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator( name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "user_id_seq" )
    private Long id;
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String lastName;
    @JsonProperty
    private String username;
    private String password;
    @JsonProperty
    private LocalDate birthdate;
    @Type( type = "org.hibernate.type.NumericBooleanType" )
    private Boolean active = Boolean.TRUE;
    private LocalDateTime tokenExpiration;
    private String token;

    protected User()
    {
    }

    @JsonIgnore
    public Boolean getActive()
    {
        return active;
    }

    public void setActive(
        final Boolean active )
    {
        this.active = active;
    }

    @Size( min = 1, max = 255 )
    @NotEmpty
    public String getFirstName()
    {
        return firstName;
    }

    @NotEmpty
    @Size( min = 1, max = 255 )
    public String getLastName()
    {
        return lastName;
    }

    @Override
    @NotEmpty
    @Size( min = 1, max = 60 )
    public String getUsername()
    {
        return username;
    }

    @Override
    @NotEmpty
    @Size( min = 8, max = 255 )
    @JsonProperty( access = Access.WRITE_ONLY )
    public String getPassword()
    {
        return password;
    }

    public void setPassword(
        final String password )
    {
        this.password = password;
    }

    @Past
    @NotNull
    public LocalDate getBirthdate()
    {
        return birthdate;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(
        final Long id )
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return toStringHelper( this ).add( "id", id ).add( "firstName", firstName ).add( "lastName",
            lastName ).add( "username", username ).add( "birthdate", birthdate ).add( "active",
                active ).toString();
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return new ArrayList<>();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled()
    {
        return true;
    }

    @JsonIgnore
    public LocalDateTime getTokenExpiration()
    {
        return tokenExpiration;
    }

    public void setTokenExpiration(
        final LocalDateTime tokenExpiration )
    {
        this.tokenExpiration = tokenExpiration;
    }

    @JsonIgnore
    public String getToken()
    {
        return token;
    }

    public void setToken(
        final String token )
    {
        this.token = token;
    }
}