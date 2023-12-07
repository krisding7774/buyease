package com.forum.api.service.mybatis.mapper.customerize;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.forum.api.vo.RegisterReqVo;

public interface RegisterMapper {

	@Insert({
		"<script>"
		, "INSERT INTO sys_account ("
		, "user_id, user_name, user_role, password_hash, status, mail, enable_mark, crt_date"		
		, ") VALUES ("
		, "#{record.userId}, #{record.userName}, #{record.userRole}, #{record.password}, "
		, "#{record.status}, #{record.mail}, #{record.enableMark}, #{record.crtDate})"
		, "</script>"
	})
	int registerUserInfo(@Param("record") RegisterReqVo reqVo);
}
