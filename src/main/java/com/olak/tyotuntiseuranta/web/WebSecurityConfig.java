package com.olak.tyotuntiseuranta.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
@EnableMethodSecurity (securedEnabled = true)
public class WebSecurityConfig {
    @Bean 
    public BCryptPasswordEncoder passWordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean 
    public SecurityFilterChain configurointi(HttpSecurity http) throws Exception{   
        http
            .authorizeHttpRequests(authtorize-> authtorize
                .requestMatchers("/login", "/h2-console**", "/style.css").permitAll()
                .anyRequest().authenticated()
            )
            
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**")
            )

            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
            )
            .formLogin(login -> login 
                .loginPage("/login")
                .defaultSuccessUrl("/work", true)
                .permitAll()
            )

            .logout(logout->logout
                .permitAll()
            );

        return http.build();
    } 
}
