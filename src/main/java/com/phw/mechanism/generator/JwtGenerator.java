package com.forum.mechanism.generator;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Configuration
public class JwtGenerator {

	private final String secret;
	private final Long expirationMillis;
	private Key key;

	public JwtGenerator(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") Long expirationMillis) {
		this.secret = secret;
		this.expirationMillis = expirationMillis;
		initKey();
	}

	private void initKey() {
		this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
	}

	/**
	 * 生成一個新的JWT token。
	 * <p>
	 * 此方法根據提供的使用者名稱生成一個新的JWT token。 該token的到期時間是根據此類別中的expirationMillis字段設置的。
	 *
	 * @param username 用戶名，將作為token的主題(subject)
	 * @return 一個簽名的JWT token
	 */
	public String generateToken(String username) {
		Date now = new Date();
		Date expiration = new Date(System.currentTimeMillis() + expirationMillis);

		return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(expiration).signWith(key).compact();
	}

	/**
	 * 驗證提供的JWT token。
	 * <p>
	 * 此方法解析提供的token並檢查其有效性。 它使用此類別中的key來驗證token的簽名。 如果token有效，它將返回token的claims。
	 *
	 * @param token 要驗證的JWT token
	 * @return token的Claims，如果token有效
	 * @throws io.jsonwebtoken.JwtException 如果token無效或過期
	 */
	public Claims verifyToken(String token) throws JwtException {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}

}
