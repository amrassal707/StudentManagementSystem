package com.example.StudentManagementSystem.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        // define query to retrieve a suer by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?"
        ); // ? is a placeholder


        // define query to get the roles by username FK
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");
        return jdbcUserDetailsManager; // spring security will use JDBCAUTH with our dataSource
        // follow spring security schema design, so it will be able to do all the heavy lifting for you
    }
    @Bean // it's a bean registered in spring security dependency to authorize roles
    // HTTP security  It allows configuring web based security for specific http request
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                configurer -> configurer
                        .requestMatchers(HttpMethod.GET , "/students").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST , "/students").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET , "/students/delete/**").hasRole("ADMIN")
        );
        // we are using basic auth
        httpSecurity.httpBasic();

        // disable CSRF . not required for stateless api
        httpSecurity.csrf().disable();
        return httpSecurity.build(); // return the given info above

    }
}
