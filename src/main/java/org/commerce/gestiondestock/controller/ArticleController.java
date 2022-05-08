package org.commerce.gestiondestock.controller;

import org.commerce.gestiondestock.controller.api.ArticleApi;
import org.commerce.gestiondestock.dto.ArticleDto;
import org.commerce.gestiondestock.services.ArticleService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.commerce.gestiondestock.utils.Constants.APP_ROOT;

@RestController
public class ArticleController implements ArticleApi {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    @PostMapping(value = APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto save(@RequestBody ArticleDto articleDto) {
        return articleService.save(articleDto);
    }

    @Override
    @GetMapping(value = APP_ROOT+ "/articles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto findById(@PathVariable("id") Integer id) {
        return articleService.findById(id);
    }

    @Override
    @GetMapping(value = APP_ROOT+ "/articles/byCode/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle) {
        return articleService.findByCodeArticle(codeArticle);
    }

    @Override
    @GetMapping(value = APP_ROOT+ "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArticleDto> findAll() {
        return articleService.findAll();
    }

    @Override
    @DeleteMapping(value = APP_ROOT + "/articles/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        articleService.delete(id);
    }
}
