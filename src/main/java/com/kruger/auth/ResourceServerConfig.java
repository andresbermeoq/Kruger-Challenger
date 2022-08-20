package com.kruger.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll()
								.antMatchers(HttpMethod.GET, "/api/v1/admin/**").hasAnyRole("ADMIN")
								.antMatchers(HttpMethod.POST, "/api/v1/employee").hasAnyRole("ADMIN")
								.antMatchers(HttpMethod.GET, "/api/v1/employee").hasAnyRole("ADMIN")
								.antMatchers(HttpMethod.DELETE, "/api/v1/employee").hasAnyRole("ADMIN")
								.antMatchers(HttpMethod.PUT, "/api/v1/employee").hasAnyRole("USER", "ADMIN")
								.anyRequest().authenticated();
	}
	
	
}
