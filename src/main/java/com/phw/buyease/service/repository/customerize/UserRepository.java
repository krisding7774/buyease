package com.forum.api.service.repository.customerize;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.forum.api.service.mybatis.mapper.SysAccountMapper;
import com.forum.api.service.mybatis.mapper.customerize.RegisterMapper;
import com.forum.api.service.mybatis.model.SysAccount;
import com.forum.api.vo.LoginReqVo;
import com.forum.api.vo.RegisterReqVo;
import com.forum.mechanism.connector.MyBatisConnector;

@Repository
public class UserRepository {
	private static Logger logger = LogManager.getLogger(UserRepository.class);

	@Autowired
	private MyBatisConnector connector;

	public int registerUserInfo(final RegisterReqVo reqVo, final SqlSession sqlSession) throws Exception {
		RegisterMapper mapper = sqlSession.getMapper(RegisterMapper.class);
		return mapper.registerUserInfo(reqVo);
	}

	public SysAccount queryUserInfo(final LoginReqVo reqVo) {
		try (SqlSession sqlSession = connector.createSqlSessionFactory().openSession()) {
			SysAccountMapper mapper = sqlSession.getMapper(SysAccountMapper.class);
			return mapper.selectByPrimaryKey(reqVo.getIdentifier());
		} catch (Exception e) {
			logger.error("Error querying user info", e);
			return null;
		}
	}

}
