package com.forum.api.vo;

import com.forum.mechanism.annotation.EmailOrPhone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginReqVo {

	@EmailOrPhone
	private String identifier;

	@NotBlank(message = "密碼不能為空")
	@Size(min = 8, max = 30, message = "密碼必須是8到30個字符")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).+$", message = "密碼必須包含至少一個小寫字母、一個大寫字母、一個數字和一個特殊字符")
	private String entryCode;

}
