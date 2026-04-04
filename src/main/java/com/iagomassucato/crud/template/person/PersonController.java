package com.iagomassucato.crud.template.person;

import com.iagomassucato.crud.template.shared.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/crud")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonResponse> createPerson(@Valid @RequestBody PersonRequest personRequest){
        PersonResponse personResponse = personService.createPerson(personRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> replacePerson(
            @PathVariable Long id,
            @Valid @RequestBody PersonRequest personRequest){
        PersonResponse personResponse = personService.replacePerson(id, personRequest);

        return ResponseEntity.ok(personResponse);
    }

    @GetMapping
    public ResponseEntity<PageResponse<PersonResponse>> findAllPagePerson(Pageable pageable) {
        Page<PersonEntity> personEntityPage = personService.findAllPagePerson(pageable);
        Page<PersonResponse> personResponsePage = personEntityPage.map(PersonResponse::fromEntity);
        PageResponse<PersonResponse> response = PageResponse.of(personResponsePage);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> findByIdPerson(@PathVariable Long id) {
        PersonResponse personResponse = personService.findByIdPerson(id);

        return ResponseEntity.ok(personResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
