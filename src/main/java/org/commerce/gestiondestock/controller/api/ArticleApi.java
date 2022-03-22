package org.commerce.gestiondestock.controller.api;

import org.commerce.gestiondestock.dto.ArticleDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.commerce.gestiondestock.utils.Constants.APP_ROOT;

public interface ArticleApi {

    @PostMapping(value = APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto save(@RequestBody  ArticleDto articleDto);

    @GetMapping(value = APP_ROOT+ "/articles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto findById(@PathVariable("id") Integer id);

    @GetMapping(value = APP_ROOT+ "/articles/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

    @GetMapping(value = APP_ROOT+ "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ArticleDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/articles/delete/{id}")
    void delete(@PathVariable("id") Integer id);
}
