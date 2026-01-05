package org.example.bnx.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {
    @NotEmpty(message = "this cant be empty")
    @NotBlank(message = "firstname is mandotary")
    private String firstName;
    @NotEmpty(message = "this cant be empty")
    @NotBlank(message = "rstname is mandotary")
    private String lastName;
    @NotEmpty(message = "this cant be empty")
    @NotBlank(message = " is mandotary")
    @Email(message = "not valid")
    private String email;
    @NotEmpty(message = "this cant be empty")
    @Size(message="min 8 character")
    @NotBlank(message = "x is mandotary")
    private String password;
}
