package com.iagomassucato.crud.template.person;

import com.iagomassucato.crud.template.exception.DomainValidationException;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "person",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_person_email", columnNames = "email"),
                @UniqueConstraint(name = "uk_person_cpf", columnNames = "cpf")
        }
)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private Integer age;

    @Builder
    public PersonEntity(
            String firstName,
            String lastName,
            String email,
            String cpf,
            Integer age
    ){
        this.firstName = validateString(firstName, "firstName");
        this.lastName = validateString(lastName, "lastName");
        this.email = validateString(email, "email");
        this.cpf = validateString(cpf, "cpf");
        this.age = validateAge(age);
    }

    public void updateFirstName(String firstName) {
        this.firstName = validateString(firstName, "firstName");
    }

    public void updateLastName(String lastName) {
        this.lastName = validateString(lastName, "lastName");
    }

    public void updateEmail(String email) {
        this.email = validateString(email, "email");
    }

    public void updateCpf(String cpf) {
        this.cpf = validateString(cpf, "cpf");
    }

    public void updateAge(Integer age) {
        this.age = validateAge(age);
    }

    private String validateString(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new DomainValidationException(fieldName, fieldName + " is required");
        }
        return value;
    }

    private Integer validateAge(Integer age) {
        if (age == null || age <= 0) {
            throw new DomainValidationException("age", "age is required");
        }
        return age;
    }
}