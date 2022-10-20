package com.lomari.walletapp.validators;


import com.lomari.walletapp.repository.UserRepository;
import com.lomari.walletapp.validators.annotations.PasswordConstraint;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<PasswordConstraint, String> {

    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return s != null
                && !StringUtils.isEmpty(s)
                && !StringUtils.isBlank(s)
                && s.matches(passwordPattern);
    }
}
