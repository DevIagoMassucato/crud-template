package com.iagomassucato.crud.template.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity,Long> {


    boolean existsByEmail(String email);

    boolean existsByCpf(Long cpf);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByCpfAndIdNot(Long cpf, Long id);
}
