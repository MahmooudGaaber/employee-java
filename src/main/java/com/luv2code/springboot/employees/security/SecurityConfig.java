package com.luv2code.springboot.employees.security;

import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {


    // get users with roles form jdbc
    @Bean
    public UserDetailsManager userDetailsManager (DataSource dataSource){
       JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

       // define query to retrieve user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, password, active from system_users where user_id=?"
        );

        // define query to retrieve the roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception {

        httpSecurity.authorizeHttpRequests(
                configurer -> configurer
                        .requestMatchers("/docs/**","/swagger-ui/**","/v3/api-docs/**","/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.GET , "/h2-console/**").permitAll()
                        .requestMatchers(HttpMethod.POST , "/h2-console/**").permitAll()
                        .requestMatchers(HttpMethod.GET , "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET , "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST , "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT , "/api/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE , "/api/employees/**").hasRole("ADMIN")
        );
        httpSecurity.httpBasic(httpBasicCustomizer -> httpBasicCustomizer.disable());
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.exceptionHandling(
                exceptionHandlingError -> exceptionHandlingError
                        .authenticationEntryPoint(authenticationEntryPoint())
        );
        httpSecurity.headers(headers -> headers.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()) );
        return  httpSecurity.build();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return ((request, response, authException) -> {
           // send 401 unauthorized without triggering a basic auth
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            // remove the WWW-Authenticate header to prevent browser
            response.setHeader("WWW-Authenticate","");
            response.getWriter().write("{\"error\"UNAUTHORIZED Access \"}");
        });
    }


}





