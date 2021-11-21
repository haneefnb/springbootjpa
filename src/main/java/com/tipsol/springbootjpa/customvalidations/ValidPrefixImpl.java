package com.tipsol.springbootjpa.customvalidations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidPrefixImpl implements ConstraintValidator<ValidPrefix, String>{

	@Override
	public boolean isValid(String input, ConstraintValidatorContext context) {
		return input.startsWith("P");
	}

}
