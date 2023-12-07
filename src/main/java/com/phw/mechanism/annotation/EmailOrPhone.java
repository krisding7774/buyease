package com.forum.mechanism.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.forum.mechanism.validator.EmailOrPhoneValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented // 表示這個annotation會被文件化
@Constraint(validatedBy = EmailOrPhoneValidator.class) // 指定實際進行驗證的類別
@Target({ ElementType.METHOD, ElementType.FIELD }) // 定義這個annotation可以被應用在哪些Java元素上，這裡是方法和資料成員
@Retention(RetentionPolicy.RUNTIME) // 定義這個annotation的生命週期
public @interface EmailOrPhone {

	String message() default "無效的電子郵件或手機號碼";

	// 用來指定這個注釋應用到哪一個或哪幾個分組，分組允許你在不同的情況下應用不同的驗證規則。
	Class<?>[] groups() default {};

	// 用來提供一些有關驗證失敗的額外資訊，可以是任何實現了Payload接口的類型。
	Class<? extends Payload>[] payload() default {};

}
