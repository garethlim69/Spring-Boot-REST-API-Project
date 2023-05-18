package com.gareth.springbootassessment.usersystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gareth.springbootassessment.usersystem.jwt.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtReqFilter;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	return authenticationConfiguration.getAuthenticationManager();
	}
	
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
			provider.setUserDetailsService(userDetailsService);
			provider.setPasswordEncoder(passwordEncoder());
			return provider;
	}
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
		
		https	
        		.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/hello").permitAll()
                .antMatchers(HttpMethod.POST, "/api/signup", "/api/session/signin").permitAll()
        		.anyRequest().authenticated()
        		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        		.and().addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class);

        return https.build();
    }
	
}
