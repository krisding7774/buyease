package com.forum.mechanism;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forum.mechanism.generator.JwtGenerator;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthorizationCheckFilter extends OncePerRequestFilter {

	@Autowired
	private JwtGenerator jwtGenerator;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
		// 如果不是登入就攔截
		if (!req.getServletPath().equals("/forum/api/login")) {
			String authorHeader = req.getHeader("AUTHORIZATION");
			String bearer = "Bearer ";
			// 以jjwt驗證token，只要驗證成功就放行
			// 驗證失敗會拋exception，直接將錯誤訊息傳回
			if (authorHeader != null && authorHeader.startsWith(bearer)) {
				try {
					String token = authorHeader.substring(bearer.length());
					Claims claims = jwtGenerator.verifyToken(token);

					System.out.println("JWT payload:" + claims.toString());

					chain.doFilter(req, res);

				} catch (Exception e) {
					System.err.println("Error : " + e);
					res.setStatus(HttpServletResponse.SC_FORBIDDEN);

					Map<String, String> err = new HashMap<>();
					err.put("jwt_err", e.getMessage());
					res.setContentType("Test123");
					new ObjectMapper().writeValue(res.getOutputStream(), err);
				}
			} else {
				res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
		} else {
			chain.doFilter(req, res);
		}
	}

}
