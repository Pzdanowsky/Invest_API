package com.Invest.system.config;


import com.Invest.system.Repositories.UserRepository;
import com.Invest.system.Services.BookaroUserDetailsManager;
import com.Invest.system.security.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import javax.sql.DataSource;

@Configuration

public class SecurityConfig extends WebSecurityConfigurerAdapter{


    private final DataSource dataSource;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final RestAuthenticationFailureHandler failureHandler;
    private final RestAuthenticationSuccesHandler succesHandler;
    private final String secret;


    public SecurityConfig(DataSource dataSource, UserRepository userRepository, ObjectMapper objectMapper,
                          RestAuthenticationFailureHandler failureHandler,
                          RestAuthenticationSuccesHandler succesHandler,@Value("${jwt.secret}") String secret) {
        this.dataSource = dataSource;
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.failureHandler = failureHandler;
        this.succesHandler = succesHandler;
        this.secret = secret;
    }

    @Override
        protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
            http
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET,"/users","/swagger-ui.html", "/v2/api-docs", "/webjars/**", "/swagger-resources/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/registration", "/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .addFilter(authenticationFilter())
                    .addFilter(new JwtAuthorizationFilter(authenticationManager(),userDetailsManager(),secret))
                    .exceptionHandling()
                    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));



        }

        @Bean
        public UserDetailsManager userDetailsManager(){
        return new BookaroUserDetailsManager(userRepository);
        }




    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception{
     JsonObjectAuthenticationFilter authenticationFilter = new JsonObjectAuthenticationFilter(objectMapper);
     authenticationFilter.setAuthenticationFailureHandler(failureHandler);
     authenticationFilter.setAuthenticationSuccessHandler(succesHandler);
     authenticationFilter.setAuthenticationManager(super.authenticationManager());
     return authenticationFilter;
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
    }




    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        BookaroUserDetailsManager detailsService = new BookaroUserDetailsManager(userRepository);
        provider.setUserDetailsService(detailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
