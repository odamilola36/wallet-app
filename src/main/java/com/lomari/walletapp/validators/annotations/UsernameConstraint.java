package com.lomari.walletapp.validators.annotations;

import com.lomari.walletapp.validators.PasswordConstraintValidator;
import com.lomari.walletapp.validators.UsernameConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.*;


@NotNull
@Size(min = 2, message = "username should be longer than one character")
@Documented
@Constraint(validatedBy = UsernameConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameConstraint {
    String message() default "username taken";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
