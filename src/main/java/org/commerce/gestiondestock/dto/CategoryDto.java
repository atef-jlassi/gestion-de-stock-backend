package org.commerce.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.commerce.gestiondestock.models.Category;

import java.util.List;

@Data
@Builder
public class CategoryDto {

    private Integer id;

    private String code;

    private String designation;

    @JsonIgnore
    private List<ArticleDto> articles;

    // Category -> CategoryDto
    public static CategoryDto fromEntity(Category category) {
        if (category == null) {
            // TODO throw an exception
            return null;
        }

        return CategoryDto.builder()
                .id(category.getId())
                .code(category.getCode())
                .designation(category.getDesignation())
                .build();
    }

    // CategoryDto -> Category
    public static Category toEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            // TODO throw an exception
            return null;
        }

        Category category = new Category();
            category.setId(categoryDto.getId());
            category.setCode(categoryDto.getCode());
            category.setDesignation(categoryDto.getDesignation());

        return category;
    }

}
