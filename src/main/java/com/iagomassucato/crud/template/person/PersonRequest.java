package com.iagomassucato.crud.template.person;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PersonRequest(
        @NotBlank(message = "firstName is invalid")
        String firstName,
        @NotBlank(message = "lastName is invalid")
        String lastName,
        @Email(message = "invalid Email")
        @NotBlank(message = "email is invalid")
        String email,
        @NotNull(message = "cpf is invalid")
        Long cpf,
        @NotNull(message = "age is invalid")
        Integer age) {
}