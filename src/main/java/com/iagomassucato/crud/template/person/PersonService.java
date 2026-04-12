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

    public PersonEntity save(PersonEntity personEntity){
        return personRepository.save(personEntity);
    }

    public PersonEntity toEntity(PersonPostRequest personPostRequest){
        return PersonEntity.builder()
                .firstName(personPostRequest.firstName())
                .lastName(personPostRequest.lastName())
                .email(personPostRequest.email())
                .cpf(personPostRequest.cpf())
                .age(personPostRequest.age())
                .build();
    }

    public Page<PersonEntity> findAllPage(Pageable pageable) {
        return personRepository.findAll(pageable);
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

    public PersonResponse findById(Long id) {
        return PersonResponse.fromEntity(findByIdOrThrow(id));
    }

    public PersonResponse update(Long id, PersonPatchRequest request) {

        PersonEntity entity = findByIdOrThrow(id);

        personValidator.validateUniqueFields(
                request.email(),
                request.cpf(),
                id
        );

        if (request.firstName() != null) {
            entity.updateFirstName(request.firstName());
        }

        if (request.lastName() != null) {
            entity.updateLastName(request.lastName());
        }

        if (request.email() != null) {
            entity.updateEmail(request.email());
        }

        if (request.cpf() != null) {
            entity.updateCpf(request.cpf());
        }

        if (request.age() != null) {
            entity.updateAge(request.age());
        }

        PersonEntity saved = personRepository.save(entity);

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

    public void delete(Long id){
        PersonEntity personEntity = findByIdOrThrow(id);
        personRepository.delete(personEntity);
    }
}
