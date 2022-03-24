package org.commerce.gestiondestock.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.commerce.gestiondestock.dto.CategoryDto;
import org.commerce.gestiondestock.exception.EntityNotFoundException;
import org.commerce.gestiondestock.exception.ErrorCodes;
import org.commerce.gestiondestock.exception.InvalidEntityException;
import org.commerce.gestiondestock.exception.InvalidOperationException;
import org.commerce.gestiondestock.models.Article;
import org.commerce.gestiondestock.repository.ArticleRepository;
import org.commerce.gestiondestock.repository.CategoryRepository;
import org.commerce.gestiondestock.services.CategoryService;
import org.commerce.gestiondestock.validators.CategoryValidator;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ArticleRepository articleRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ArticleRepository articleRepository) {
        this.categoryRepository = categoryRepository;
        this.articleRepository = articleRepository;
    }


    @Override
    public CategoryDto save(CategoryDto dto) {
        List<String> errors = CategoryValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Article is not valid {}", dto);
            throw new InvalidEntityException("La categorie n'est pas valide ", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }
        return CategoryDto.fromEntity(categoryRepository.save(CategoryDto.toEntity(dto)));
    }

    @Override
    public CategoryDto findById(Integer id) {
        if (id==null) {
            log.error("Category id is null");
            return null;
        }
        return categoryRepository.findById(id).map(CategoryDto::fromEntity).orElseThrow(()->new EntityNotFoundException(
                "Aucne categorie avec l'ID = " +id + " n'ete trouve dans la BDD",
                ErrorCodes.CATEGORY_NOT_FOUND
        ));
    }

    @Override
    public CategoryDto findByCode(String code) {
        if (code==null) {
            log.error("Category Code is null");
            return null;
        }
        return categoryRepository.findCategoryByCode(code).map(CategoryDto::fromEntity).orElseThrow(()->new EntityNotFoundException(
                "Aucune categorie avec le CODE ="+ code +" n'ete trouve dans la BDD", ErrorCodes.CATEGORY_NOT_FOUND
        ));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                                 .map(CategoryDto::fromEntity)
                                 .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null) {
            log.error("Categoy id is null");
            return;
        }
        List<Article> articles = articleRepository.findAllByCategoryId(id);
        if (!articles.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer une catégorie déja utilisé ", ErrorCodes.CATEGORY_ALREADY_IN_USE);
        }

        categoryRepository.deleteById(id);
    }
}
