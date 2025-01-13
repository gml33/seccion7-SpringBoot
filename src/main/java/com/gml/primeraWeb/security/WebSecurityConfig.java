package com.gml.primeraWeb.security;

import com.gml.primeraWeb.services.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    //DaoAuthenticationProvider es una implementacion del AuthenticationProvider
    //se usa para autenticar usuarios en bases de datos.
    //es el responsable de verificar las credenciales del usuraio y atenticarlo.

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        return authenticationManagerBuilder.build();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/personas").permitAll()
                        .requestMatchers("/personas/nueva").hasAnyRole("ADMIN")
                        .requestMatchers("/personas/editar/*", "/personas/eliminar/*").hasAnyRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(form ->form
                        .loginPage("/login")
                        .permitAll())
                .logout(l -> l.permitAll())
                .exceptionHandling(e -> e.accessDeniedPage("/403"));
        return httpSecurity.build();
    }
}
