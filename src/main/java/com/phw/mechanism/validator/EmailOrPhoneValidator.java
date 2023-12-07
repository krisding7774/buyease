package com.forum.mechanism.validator;

import com.forum.mechanism.annotation.EmailOrPhone;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// 實現ConstraintValidator介面，第一個參數是你的自訂義註解，第二個參數是你要驗證的數據類型
public class EmailOrPhoneValidator implements ConstraintValidator<EmailOrPhone, String> {

	private String emailRegex;
	private String phoneRegex;

	@Override
	public void initialize(EmailOrPhone constraintAnnotation) {
		emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
		phoneRegex = "^09[0-9]{8}$";
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (null == value || value.trim().isEmpty()) {
			return false;
		}

		return value.matches(emailRegex) || value.matches(phoneRegex);
	}
}
