package com.example.cookingBlog.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import validators.DifferentNames;
import validators.ValidPassword;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DifferentNames
public class SignUpForm {

    @Size(min = 4, max = 20)
    @NotBlank
    private String firstName;
    @Size(min = 4, max = 20)
    @NotBlank
    private String lastName;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @ValidPassword
    private String password;
}
