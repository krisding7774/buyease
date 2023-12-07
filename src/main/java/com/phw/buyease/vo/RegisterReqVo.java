package com.forum.api.vo;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterReqVo {

	@NotBlank(message = "信箱不得為空")
	@Email(message = "無效的信箱格式")
	private String email;

	@NotBlank(message = "帳號不得為空")
	@Size(min = 8, max = 15, message = "帳號必須是8到15個字符")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "帳號只能包含英文和數字")
	private String account;

	@NotBlank(message = "暱稱不得為空")
	@Pattern(regexp = "^[\\u4e00-\\u9fa5a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+$", message = "暱稱格式不正確")
	private String nickName;

	@NotBlank(message = "密碼不得為空")
	@Size(min = 8, max = 30, message = "密碼必須是8到30個字符")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).+$", message = "密碼必須包含至少一個小寫字母、一個大寫字母、一個數字和一個特殊字符")
	private String entryCode;

	private String userRole;

	private Integer userStatus;

	private String enableMark;

	private Date crtDate;

}
