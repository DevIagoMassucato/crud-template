package com.iagomassucato.crud.template.person;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonPutRequest(
        @NotBlank(message = "firstName is required")
        String firstName,
        @NotBlank(message = "lastName is required")
        String lastName,
        @Email(message = "invalid email")
        @NotBlank(message = "email is required")
        String email,
        @NotNull(message = "cpf is required")
        String cpf,
        @NotNull(message = "age is required")
        Integer age) {

}
