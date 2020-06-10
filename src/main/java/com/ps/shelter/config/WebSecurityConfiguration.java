package com.ps.shelter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/v2/**",      // Swagger
                "/swagger-ui.html",
                "/*", "/pub/**",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/webjars/**",
                "/user/**",
                "/discount/**",
                "/dog/**",
                "/food/**",
                "/schedule/**",
                "/supplier/**",
                "/vet/**",
                "/basket_food/**",
                "/basket/**"
        );
        web.ignoring().antMatchers(HttpMethod.OPTIONS);
    }
}
