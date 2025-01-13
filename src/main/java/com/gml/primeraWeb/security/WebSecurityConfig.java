package com.gml.primeraWeb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails user1 = User.builder()
                .username("user")
                .password("{bcrypt}$2a$10$f5u7OO4MmyogoeRIRJJ0rOoEEAQojalBkGD2RClVcqMS1kDmxTMJK")
                .roles("USER")
                .build();

        UserDetails user2 = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$10$f5u7OO4MmyogoeRIRJJ0rOoEEAQojalBkGD2RClVcqMS1kDmxTMJK")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
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
