package com.iagomassucato.crud.template.person;

import com.iagomassucato.crud.template.exception.DomainValidationException;
import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;

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
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    private Integer age;

    @Builder
    public PersonEntity(
            String firstName,
            String lastName,
            String email,
            String cpf,
            Integer age
    ){
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.email = Objects.requireNonNull(email);
        this.cpf = Objects.requireNonNull(cpf);
        this.age = Objects.requireNonNull(age);
    }

    public void updateFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new DomainValidationException("firstName", "firstName is required");
        }
        this.firstName = firstName;
    }

    public void updateLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new DomainValidationException("lastName", "lastName is required");
        }
        this.lastName = lastName;
    }

    public void updateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new DomainValidationException("email", "email is required");
        }
        this.email = email;
    }

    public void updateCpf(String cpf) {
        if (cpf == null || cpf .isBlank()) {
            throw new DomainValidationException("cpf", "cpf is required");
        }
        this.cpf = cpf;
    }

    public void updateAge(Integer age) {
        if (age == null || age <= 0) {
            throw new DomainValidationException("age", "age is required");
        }
        this.age = age;
    }
}
