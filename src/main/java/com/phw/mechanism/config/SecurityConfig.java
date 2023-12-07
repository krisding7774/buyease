package com.forum.mechanism.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.forum.mechanism.AuthorizationCheckFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@SuppressWarnings("removal")
	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		// 暫時關掉CSRF保護
		http.csrf().disable();
		// 不寫session
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// filter
		http.addFilterBefore(new AuthorizationCheckFilter(), BasicAuthenticationFilter.class);

		return http.build();
	}

}
