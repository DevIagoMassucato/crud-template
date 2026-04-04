package com.iagomassucato.crud.template.person;

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
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long cpf;
    private Integer age;
}
