package com.iagomassucato.crud.template.person;

public record PersonResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String cpf,
        Integer age) {

    public static PersonResponse fromEntity(PersonEntity personEntity) {
        return new PersonResponse(
                personEntity.getId(),
                personEntity.getFirstName(),
                personEntity.getLastName(),
                personEntity.getEmail(),
                personEntity.getCpf(),
                personEntity.getAge()
        );
    }
}
