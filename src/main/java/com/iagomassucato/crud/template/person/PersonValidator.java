package com.iagomassucato.crud.template.person;

import com.iagomassucato.exception.core.ApiException;
import com.iagomassucato.exception.core.ErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PersonValidator {

    private final PersonRepository personRepository;

    public void validateUniqueFields(String email, String cpf, Long id) {
        Map<String, Object> errors = new LinkedHashMap<>();

        if (isEmailAlreadyUsed(email, id)) {
            errors.put("email", email);
        }

        if (isCpfAlreadyUsed(cpf, id)) {
            errors.put("cpf", cpf);
        }

        if (!errors.isEmpty()) {
            throw new ApiException(
                    ErrorEnum.DATA_INTEGRITY_VIOLATION,
                    errors);
        }
    }

    private boolean isEmailAlreadyUsed(String email, Long id) {
        return (id == null)
                ? personRepository.existsByEmail(email)
                : personRepository.existsByEmailAndIdNot(email, id);
    }

    private boolean isCpfAlreadyUsed(String cpf, Long id) {
        return (id == null)
                ? personRepository.existsByCpf(cpf)
                : personRepository.existsByCpfAndIdNot(cpf, id);
    }
}
