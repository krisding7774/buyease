package com.forum.api.service.customerize;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.api.service.repository.SysAccountRepository;

@Service
public class VerifyService {
	private static Logger logger = LogManager.getLogger(VerifyService.class);

	@Autowired
	private SysAccountRepository sysAccountRepository;

	public String isEmailPresent(final String email) {

		try {
			var sysAccount = sysAccountRepository.queryUserByPrimaryKey(email);

			if (null == sysAccount) {
				return "email is existed";
			}

		} catch (Exception e) {
			logger.error("email is existed", e);
		}

		return "temp success";
	}

}
