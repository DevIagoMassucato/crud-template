package com.iagomassucato.crud.template.person;

import jakarta.validation.constraints.Email;

public record PersonPatchRequest (
        String firstName,
        String lastName,
        @Email(message = "invalid email")
        String email,
        String cpf,
        Integer age
){

}
