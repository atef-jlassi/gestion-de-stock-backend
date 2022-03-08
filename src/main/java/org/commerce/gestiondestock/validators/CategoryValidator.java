package org.commerce.gestiondestock.validators;

import org.commerce.gestiondestock.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {

    public static List<String> validate(CategoryDto categoryDto) {
            List<String> errors = new ArrayList<>();

            if (categoryDto==null || !StringUtils.hasLength(categoryDto.getCode())) {
                errors.add("Veuillez renseigner le code de la cateogrie");
            }
            return errors;
    }
}
