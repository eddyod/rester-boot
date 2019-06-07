package com.mephistosoftware.rester.view;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.RetentionPolicy;

@Documented
@Constraint(validatedBy=PhoneConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
	
	// sub the message below and edit message_XX.properties
	// String message() default "{Phone}";
	String message() default "Not a valid phone number!!!!";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default{};

}
