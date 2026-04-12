package com.iagomassucato.crud.template.person;

public record PersonPatchRequest (
        String firstName,
        String lastName,
        String email,
        String cpf,
        Integer age
){

}
