package com.lomari.walletapp.validators;

import com.lomari.walletapp.enums.Currency;
import com.lomari.walletapp.validators.annotations.CurrencyConstraint;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CurrencyConstraintValidator implements ConstraintValidator<CurrencyConstraint, String> {
    @Override
    public void initialize(CurrencyConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null
                && !StringUtils.isBlank(s)
                && !StringUtils.isEmpty(s)
                && Currency.isSupportedCurrency(s);
    }
}
