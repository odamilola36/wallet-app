package com.lomari.walletapp.validators.annotations;
import com.lomari.walletapp.validators.CurrencyConstraintValidator;
import com.lomari.walletapp.validators.PasswordConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CurrencyConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrencyConstraint {
    String message() default "Invalid currency ISO";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
