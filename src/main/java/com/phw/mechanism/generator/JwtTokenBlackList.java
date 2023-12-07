package com.forum.mechanism.generator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenBlackList {

	private Map<String, Date> blackMap = new HashMap<>();

	public void addToken(String token) {
		Date now = new Date();
		blackMap.put(token, now);
	}

	public boolean isTokenBlacklisted(String token) {
		Date expiration = blackMap.get(token);
		return expiration != null && expiration.after(new Date());
	}

}
