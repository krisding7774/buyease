package com.forum.api.service.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.forum.api.service.mybatis.mapper.SysAccountMapper;
import com.forum.api.service.mybatis.model.SysAccount;
import com.forum.mechanism.connector.MyBatisConnector;

@Repository
public class SysAccountRepository {

	@Autowired
	private MyBatisConnector conn;

	public SysAccount queryUserByPrimaryKey(final String email, final SqlSession sqlSession) throws Exception {
		SysAccountMapper mapper = sqlSession.getMapper(SysAccountMapper.class);
		return mapper.selectByPrimaryKey(email);
	}

	public SysAccount queryUserByPrimaryKey(final String email) throws Exception {
		SqlSession sqlSession = conn.createSqlSessionFactory().openSession();
		SysAccountMapper mapper = sqlSession.getMapper(SysAccountMapper.class);
		return mapper.selectByPrimaryKey(email);
	}

}
