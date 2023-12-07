package com.forum.api.service.customerize;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.forum.api.enums.UserRole;
import com.forum.api.service.mybatis.model.SysAccount;
import com.forum.api.service.repository.SysAccountRepository;
import com.forum.api.service.repository.customerize.UserRepository;
import com.forum.api.vo.LoginReqVo;
import com.forum.api.vo.RegisterReqVo;
import com.forum.mechanism.connector.MyBatisConnector;
import com.forum.mechanism.generator.JwtGenerator;

import io.jsonwebtoken.Claims;

@Service
public class AuthService {
	private static Logger logger = LogManager.getLogger(AuthService.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MyBatisConnector connector;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SysAccountRepository sysAccountRepository;

	@Autowired
	private JwtGenerator jwtGenerator;

	public String registerUserInfo(final RegisterReqVo reqVo) {
		int count = 0;

		try (SqlSession sqlSession = connector.createSqlSessionFactory().openSession()) {
			var sysAccount = sysAccountRepository.queryUserByPrimaryKey(reqVo.getEmail(), sqlSession);

			if (null == sysAccount) {
				return "user is existed";
			}

			reqVo.setUserRole(UserRole.REGULAR.getRoleName());
			reqVo.setEntryCode(encodePassword(reqVo.getEntryCode()));
			reqVo.setUserStatus(1);
			reqVo.setEnableMark("N");
			reqVo.setCrtDate(new Date());

			count = userRepository.registerUserInfo(reqVo, sqlSession);

			sqlSession.commit();
		} catch (Exception e) {
			logger.error("Error registering user info", e);
		}

		return count == 1 ? "register success" : "register fail";
	}

	public String verifyUser(final LoginReqVo reqVo) {
		SysAccount sysAccount = userRepository.queryUserInfo(reqVo);

		if (null == sysAccount) {
			return "Account not found";
		}

		if (!isPasswordValid(reqVo.getEntryCode(), sysAccount.getPasswordHash())) {
			return "verifyFail";
		}

		return jwtGenerator.generateToken(reqVo.getIdentifier());
	}

	public Claims testToken(final String token) {
		return jwtGenerator.verifyToken(token);
	}

	private String encodePassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	private Boolean isPasswordValid(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

}
