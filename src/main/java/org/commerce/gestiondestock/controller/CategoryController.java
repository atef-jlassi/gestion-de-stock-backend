package org.commerce.gestiondestock.controller;

import org.commerce.gestiondestock.controller.api.CategoryApi;
import org.commerce.gestiondestock.dto.CategoryDto;
import org.commerce.gestiondestock.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.commerce.gestiondestock.utils.Constants.APP_ROOT;

@RestController
public class CategoryController implements CategoryApi {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto save(@RequestBody CategoryDto dto) {
        return categoryService.save(dto);
    }

    @Override
    @GetMapping(value = APP_ROOT+ "/categories/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto findById(@PathVariable("id") Integer id) {
        return categoryService.findById(id);
    }

    @Override
    @GetMapping(value = APP_ROOT+ "/categories/byCode/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto findByCode(@PathVariable("codeCategory") String code) {
        return categoryService.findByCode(code);
    }

    @Override
    @GetMapping(value = APP_ROOT+ "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @Override
    @DeleteMapping(value = APP_ROOT + "/categories/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        categoryService.delete(id);
    }
}
