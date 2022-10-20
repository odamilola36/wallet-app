package com.lomari.walletapp.validators.annotations;


import com.lomari.walletapp.validators.EmailConstraintValidator;
import com.lomari.walletapp.validators.PasswordConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;
@Email(message = "not a valid email address")
@NotNull
@Documented
@Constraint(validatedBy = EmailConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailConstraint {
    String message() default "email address already used";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
