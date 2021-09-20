package com.lhind.project.annualleave.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private TokenProvider tokenProvider;
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private JwtAuthenticationEntryPoint authenticationErrorHandler;

    @Autowired
    public WebSecurityConfig(TokenProvider tokenProvider, JwtAccessDeniedHandler jwtAccessDeniedHandler, JwtAuthenticationEntryPoint authenticationErrorHandler) {
        this.tokenProvider = tokenProvider;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.authenticationErrorHandler = authenticationErrorHandler;
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        List<String> allowOrigins = Arrays.asList("*");
        configuration.setAllowedOriginPatterns(allowOrigins);
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setExposedHeaders(Collections.singletonList(HttpHeaders.AUTHORIZATION));
        // in case authentication is enabled this flag MUST be set, otherwise CORS requests will fail
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(

                        "/api/user/login",
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config",
                        "/swagger-resources/**",
                        "/v3/api-docs/**",
                        "/api/user/change-password"


                );
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        ;
        httpSecurity
                .csrf()
                .disable()
                .addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(authenticationErrorHandler)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/application/leave/request")
                .hasAuthority("SUPERVISOR")
                .antMatchers("/api/user/change-password")
                .hasAuthority("SUPERVISOR")
                .antMatchers("/api/application/leave/pending-list")
                .hasAuthority("SUPERVISOR")
                .antMatchers("/api/user/**")
                .hasAuthority("ADMIN")
                .antMatchers("/api/user/change-password")
                .hasAuthority("ADMIN")
                .antMatchers("/api/application/leave")
                .hasAuthority("USER")
                .antMatchers("/api/application/leave/list")
                .hasAuthority("USER")
                .antMatchers("/api/user/change-password")
                .hasAuthority("USER")
                .antMatchers("/api/application/leave/{id}")
                .hasAuthority("USER")
                .and()
                .apply(securityConfigurerAdapter());
    }


    private JwtConfigurer securityConfigurerAdapter() {
        return new JwtConfigurer(this.tokenProvider);
    }
}
