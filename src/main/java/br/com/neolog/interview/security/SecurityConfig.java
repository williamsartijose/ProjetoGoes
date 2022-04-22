package br.com.neolog.interview.security;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.google.common.collect.ImmutableList;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( prePostEnabled = true )
class SecurityConfig
    extends
        WebSecurityConfigurerAdapter
{
    private static final List<String> PUBLIC_PATHS = ImmutableList.of( "/v1/users",
        "/v1/users/login", "/v1/**" );

    private final RequestMatcher publicUrlMatcher;
    private final RequestMatcher protectedUrlMatcher;
    private final TokenAuthenticationProvider provider;

    SecurityConfig(
        final TokenAuthenticationProvider provider )
    {
        this.provider = requireNonNull( provider );
        //@formatter:off
        final ImmutableList<RequestMatcher> publicMatchers = ImmutableList.<RequestMatcher>builder()
            .addAll( PUBLIC_PATHS.stream().map( AntPathRequestMatcher::new ).collect( toList() ) )
            .add( new NegatedRequestMatcher( new AntPathRequestMatcher( "/v1/**" ) ) )
            .build();
        //@formatter:on
        this.publicUrlMatcher = new OrRequestMatcher( publicMatchers );
        this.protectedUrlMatcher = new AndRequestMatcher(
            new NegatedRequestMatcher( publicUrlMatcher ) );
    }

    @Override
    protected void configure(
        final AuthenticationManagerBuilder auth )
    {
        auth.authenticationProvider( provider );
    }

    @Override
    public void configure(
        final WebSecurity web )
    {
        web.ignoring().requestMatchers( publicUrlMatcher );
    }

    @Override
    protected void configure(
        final HttpSecurity http )
        throws Exception
    {
        //@formatter:off
        http.sessionManagement().sessionCreationPolicy( STATELESS )
            .and()
            .exceptionHandling()
            .defaultAuthenticationEntryPointFor( forbiddenEntryPoint(), protectedUrlMatcher )
            .and().authenticationProvider( provider )
                .addFilterBefore( restAuthenticationFilter(), AnonymousAuthenticationFilter.class )
                .authorizeRequests()
                .anyRequest()
                .authenticated()
            .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable();
      //@formatter:on
    }

    @Bean
    TokenAuthenticationFilter restAuthenticationFilter()
        throws Exception
    {
        final TokenAuthenticationFilter filter = new TokenAuthenticationFilter(
            protectedUrlMatcher );
        filter.setAuthenticationManager( authenticationManager() );
        filter.setAuthenticationSuccessHandler( successHandler() );
        return filter;
    }

    @Bean
    SimpleUrlAuthenticationSuccessHandler successHandler()
    {
        final SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setRedirectStrategy( new NoRedirectStrategy() );
        return successHandler;
    }

    @Bean
    FilterRegistrationBean<TokenAuthenticationFilter> disableAutoRegistration(
        final TokenAuthenticationFilter filter )
    {
        final FilterRegistrationBean<TokenAuthenticationFilter> registration = new FilterRegistrationBean<>(
            filter );
        registration.setEnabled( false );
        return registration;
    }

    @Bean
    AuthenticationEntryPoint forbiddenEntryPoint()
    {
        return new HttpStatusEntryPoint( FORBIDDEN );
    }
}
