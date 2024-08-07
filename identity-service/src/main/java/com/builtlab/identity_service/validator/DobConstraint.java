package com.builtlab.identity_service.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.util.List;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(
        validatedBy = {DobValidator.class}
)
public @interface DobConstraint {
    String message() default "Invalid date of birth";

    int min();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
