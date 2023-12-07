package com.forum.api.service.mybatis.mapper;

import com.forum.api.service.mybatis.model.SysAccount;
import com.forum.api.service.mybatis.model.SysAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysAccountMapper {

	long countByExample(SysAccountExample example);

	int deleteByExample(SysAccountExample example);

	int deleteByPrimaryKey(String userId);

	int insert(SysAccount row);

	int insertSelective(SysAccount row);

	List<SysAccount> selectByExample(SysAccountExample example);

	SysAccount selectByPrimaryKey(String userId);

	int updateByExampleSelective(@Param("row") SysAccount row, @Param("example") SysAccountExample example);

	int updateByExample(@Param("row") SysAccount row, @Param("example") SysAccountExample example);

	int updateByPrimaryKeySelective(SysAccount row);

	int updateByPrimaryKey(SysAccount row);
}