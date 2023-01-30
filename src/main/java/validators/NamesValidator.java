package validators;

import com.example.cookingBlog.dto.SignUpForm;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NamesValidator implements ConstraintValidator<DifferentNames, SignUpForm> {
    @Override
    public boolean isValid(SignUpForm signUpForm, ConstraintValidatorContext constraintValidatorContext) {
        return !signUpForm.getFirstName().equals(signUpForm.getLastName());
    }
}
