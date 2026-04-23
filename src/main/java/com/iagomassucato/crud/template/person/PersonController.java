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
@RequestMapping("/api/v1/crud")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonResponse> create(@Valid @RequestBody PersonPostRequest personPostRequest){
        PersonResponse personResponse = personService.create(personPostRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PersonResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody PersonPatchRequest personPatchRequest){
        PersonResponse personResponse = personService.update(id, personPatchRequest);
        return ResponseEntity.ok(personResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> replace(
            @PathVariable Long id,
            @Valid @RequestBody PersonPutRequest personPutRequest){
        PersonResponse personResponse = personService.replace(id, personPutRequest);
        return ResponseEntity.ok(personResponse);
    }

    @GetMapping
    public ResponseEntity<PageResponse<PersonResponse>> findAllPage(Pageable pageable) {
        Page<PersonEntity> personEntityPage = personService.findAllPage(pageable);
        Page<PersonResponse> personResponsePage = personEntityPage.map(PersonResponse::fromEntity);
        PageResponse<PersonResponse> response = PageResponse.of(personResponsePage);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> findById(@PathVariable Long id) {
        PersonResponse personResponse = personService.findById(id);
        return ResponseEntity.ok(personResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
