package com.phw.toolkit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 禁用CSRF保護，因為我們不使用基於表單的登錄而是使用無狀態REST API
		http.csrf().disable();

		// 將會話管理設置為無狀態，因為我們是使用JWT
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// 設置授權請求
		http.authorizeRequests().anyRequest().authenticated();

		return http.build();
	}

}
