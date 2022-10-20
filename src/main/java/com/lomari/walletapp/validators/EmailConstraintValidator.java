package com.lomari.walletapp.validators;

import com.lomari.walletapp.repository.UserRepository;
import com.lomari.walletapp.validators.annotations.EmailConstraint;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailConstraintValidator implements ConstraintValidator<EmailConstraint, String> {

    private final UserRepository userRepository;

    public EmailConstraintValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(EmailConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return isUniqueEmail(s);
    }

    private boolean isUniqueEmail(String s){
        return userRepository.findByEmail(s).isEmpty();
    }
}
