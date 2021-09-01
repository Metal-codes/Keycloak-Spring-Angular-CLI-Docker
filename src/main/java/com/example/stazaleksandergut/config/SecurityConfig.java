package com.example.stazaleksandergut.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@KeycloakConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled=true)
@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    /**
     * autowired authentication manager builder for keycloak
     * @param authenticationManagerBuilder AuthenticationManagerBuilder
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) {
        var keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        authenticationManagerBuilder.authenticationProvider(keycloakAuthenticationProvider);
    }

    /**
     * Bean for keycloak springboot config
     * @return new instance of KeycloakSpringBootConfigResolver
     */
    @Bean
    public KeycloakSpringBootConfigResolver keycloakSpringBootConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    /**
     * Session auth strategy
     * @return new instance of Session Auth Strategy with SessionRegistryImpl
     */
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(
                new SessionRegistryImpl());
    }

    /**
     * HttpSecurity config not used because of RolesAllowed annotation in controllers
     * @param http HttpSecurity instance config
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .cors()
                .and().csrf().disable()
                .authorizeRequests();

                /* H2 Console */
                /*.antMatchers("/h2-console/**").hasRole("ADMIN")
                .and().headers().frameOptions().sameOrigin()
                .and().exceptionHandling().accessDeniedHandler(new AccessDeniedHandlerImpl());*/
                /* H2 Console END */
    }

    /**
     * CORS configuration bean
     * @return returns cors configuration
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost", "http://prod.gut.codes"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "CREATE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
