package com.iagomassucato.crud.template.person;

import com.iagomassucato.exception.core.ApiException;
import com.iagomassucato.exception.core.ErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonValidator personValidator;

    public PersonResponse create(PersonPostRequest personPostRequest){
        personValidator.validateUniqueFields(personPostRequest.email(), personPostRequest.cpf(), null);
        PersonEntity personEntity = toEntity(personPostRequest);
        PersonEntity personEntitySaved = save(personEntity);
        return PersonResponse.fromEntity(personEntitySaved);
    }

    public PersonResponse update(Long id, PersonPatchRequest personPatchRequest) {

        PersonEntity personEntity = findByIdOrThrow(id);

        personValidator.validateUniqueFields(
                personPatchRequest.email(),
                personPatchRequest.cpf(),
                id
        );

        if (personPatchRequest.firstName() != null) {
            personEntity.updateFirstName(personPatchRequest.firstName());
        }

        if (personPatchRequest.lastName() != null) {
            personEntity.updateLastName(personPatchRequest.lastName());
        }

        if (personPatchRequest.email() != null) {
            personEntity.updateEmail(personPatchRequest.email());
        }

        if (personPatchRequest.cpf() != null) {
            personEntity.updateCpf(personPatchRequest.cpf());
        }

        if (personPatchRequest.age() != null) {
            personEntity.updateAge(personPatchRequest.age());
        }

        PersonEntity saved = personRepository.save(personEntity);

        return PersonResponse.fromEntity(saved);
    }

    public PersonResponse replace(Long id, PersonPutRequest personPutRequest) {

        PersonEntity personEntity = findByIdOrThrow(id);

        personValidator.validateUniqueFields(
                personPutRequest.email(),
                personPutRequest.cpf(),
                id
        );

        personEntity.updateFirstName(personPutRequest.firstName());
        personEntity.updateLastName(personPutRequest.lastName());
        personEntity.updateEmail(personPutRequest.email());
        personEntity.updateCpf(personPutRequest.cpf());
        personEntity.updateAge(personPutRequest.age());
        PersonEntity personEntitySaved = personRepository.save(personEntity);

        return PersonResponse.fromEntity(personEntitySaved);
    }

    public Page<PersonEntity> findAllPage(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public PersonResponse findById(Long id) {
        return PersonResponse.fromEntity(findByIdOrThrow(id));
    }

    public void delete(Long id){
        PersonEntity personEntity = findByIdOrThrow(id);
        personRepository.delete(personEntity);
    }

    private PersonEntity save(PersonEntity personEntity){
        return personRepository.save(personEntity);
    }

    private PersonEntity toEntity(PersonPostRequest personPostRequest){
        return PersonEntity.builder()
                .firstName(personPostRequest.firstName())
                .lastName(personPostRequest.lastName())
                .email(personPostRequest.email())
                .cpf(personPostRequest.cpf())
                .age(personPostRequest.age())
                .build();
    }

    private PersonEntity findByIdOrThrow(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ApiException(
                        ErrorEnum.NOT_FOUND,
                        Map.of(
                                "id", id,
                                "message","person with id " + id + " not found")
                ));
    }
}
