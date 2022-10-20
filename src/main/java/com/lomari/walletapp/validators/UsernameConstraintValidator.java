package com.lomari.walletapp.validators;

import com.lomari.walletapp.repository.UserRepository;
import com.lomari.walletapp.validators.annotations.UsernameConstraint;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameConstraintValidator implements ConstraintValidator<UsernameConstraint, String> {

    private final UserRepository userRepository;

    public UsernameConstraintValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UsernameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.isNotEmpty(s)
                && StringUtils.isNotBlank(s)
                && isUnique(s);
    }

    private boolean isUnique(String s) {
        return userRepository.findByUsername(s).isEmpty();
    }
}
