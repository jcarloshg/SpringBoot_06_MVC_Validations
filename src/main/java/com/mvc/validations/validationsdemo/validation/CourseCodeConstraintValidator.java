package com.mvc.validations.validationsdemo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    private String coursePrefix;

    @Override
    public void initialize(CourseCode constraintAnnotation) {
        System.err.println("initialize");
        coursePrefix = constraintAnnotation.value();
    }

    // this could get the business logic
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        System.out.println("isValid");

        boolean isValid = true;

        if (value == null)
            isValid = true;

        if (value != null)
            return value.startsWith(coursePrefix);

        return isValid;
    }

}
