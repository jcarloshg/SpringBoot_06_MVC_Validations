# SpringBoot_06_MVC_Validations

Hello friends. 👋

This project contains the application about the validations provided from Java Bean Validation 🫘
Also, it manages the errors from the validations make when the data is invalid.


## Index 🚀

- [Overview](#overview-)
- [Implementation](#implementation-)
    - [Files](#files-)
    - [Overview about the flow](#overview-about-the-flow-️)
    - [Validations](#validations-)
    - [Custom Validations](#custom-validations-)
    - [Manage errors in the controller](#manage-errors-in-the-controller-)
    - [Show errors](#showing-errors-)
- [Dependencies](#dependencies-)



## Overview 👀

### Java Bean Validation 🫘
- Java has it like standard validation.
- It gives us beans to validate the data in a easy way.
    - In the beans we set the custom message to that error.
- Also, we ca make our custom validations.
- [spring-boot-starter-validation 🫘](https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html)



## Implementation 🪖

### Files 🪧

- Model:
    - [CostumerValidation.java](src/main/java/com/mvc/validations/validationsdemo/CostumerValidation.java)
    - Custom validation:
        - [CourseCode.java](src/main/java/com/mvc/validations/validationsdemo/validation/CourseCode.java)
        - [CourseCodeConstraintValidator.java](src/main/java/com/mvc/validations/validationsdemo/validation/CourseCodeConstraintValidator.java)
- View *(Templates HTMLs)*:
    - [CostumerForm.html](src/main/resources/templates/CostumerForm.html)
    - [CostumerConfirmation.html](src/main/resources/templates/CostumerConfirmation.html)
- Controller:
    - [CustomerController.java](src/main/java/com/mvc/validations/validationsdemo/CustomerController.java)


### Overview about the flow 🛤️
- ![Overview about the flow](<DOCs/images/Frame 14 (1).png>)


### Validations ✅✅

- The model must have the *validations*.
- In the model, the beans validations must have
    - The value to be comparad and the custom.
    - The message by the when the validation was failed.

```java
public class CostumerValidation {

    private String firstName;

    @NotNull(message = "Is required")
    @Size(min = 1, message = "must be greater than 1")
    private String lastName = "";

    @NotNull(message = "Is required")
    @Min(value = 0, message = "must be grater than 0 or equal to 0")
    @Max(value = 10, message = "must be less than 0 or equal to 10")
    private Integer freePasses;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "Only 5 chars/digits")
    private String postalCode;

    @CourseCode()
    private String courseCode;
}
```

### Custom validations 🧰

- The signature for the custom validator.
```java
public @interface CourseCode {
    public String value() default "LUV";
    public String message() default "must be start with 'LUV'";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
```

- Implementation of the signature for the custom validator.
```java
public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    private String coursePrefix;

    @Override
    public void initialize(CourseCode constraintAnnotation) {}


    // this could get the business logic
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {}
}
```


### Manage errors in the *Controller* 🛞

- The user sends the data to the **[http://localhost:8080/processForm](http://localhost:8080/processForm)** service.
- The controller get the request.
    - The service tries to create a new Costumer with the data.
    - The native validations are running to valida the data
    - The customs validations are running to valida the data
- The endpoint continues
- If the data is valid, so, the services sends the view [CostumerConfirmation.html](target/classes/templates/CostumerConfirmation.html)
- If the data has errors, so, the service sends the view [CostumerForm.html](target/classes/templates/CostumerForm.html)

```java
@PostMapping("/processForm")
    public String processForm(
        @Valid @ModelAttribute("costumer") CostumerValidation costumerValidation,
        BindingResult bindingResult
    ) {

    if (bindingResult.hasErrors()) return "CostumerForm";
    return "CostumerConfirmation";
}
```


### Showing errors 🔴

```html

        Postal Code(*): <input type="text" th:field="*{postalCode}">

        <span th:if="${#fields.hasErrors('postalCode')}" th:errors="*{postalCode}" class="error">
        </span>

```



## Dependencies 🧰

- spring-boot-starter-thymeleaf
- spring-boot-starter-validation
- spring-boot-starter-web
- spring-boot-devtools
- spring-boot-starter-test

[pom.xml 🪶](pom.xml)