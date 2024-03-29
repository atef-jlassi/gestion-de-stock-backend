package org.commerce.gestiondestock.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.commerce.gestiondestock.dto.ArticleDto;
import org.commerce.gestiondestock.exception.EntityNotFoundException;
import org.commerce.gestiondestock.exception.ErrorCodes;
import org.commerce.gestiondestock.exception.InvalidEntityException;
import org.commerce.gestiondestock.models.Article;
import org.commerce.gestiondestock.repository.ArticleRepository;
import org.commerce.gestiondestock.services.ArticleService;
import org.commerce.gestiondestock.validators.ArticleValidator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

  private ArticleRepository articleRepository;

  public ArticleServiceImpl(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  @Override
  public ArticleDto save(ArticleDto articleDto) {
    List<String> errors = ArticleValidator.validate(articleDto);
    if (!errors.isEmpty()) {
      log.error("Article is not valid {} ", articleDto);
      throw new InvalidEntityException("l'article n'est pas valide ", ErrorCodes.ARTICLE_NOT_VALID, errors);
    }

    Article savedArticle = articleRepository.save(ArticleDto.toEntity(articleDto));
    return ArticleDto.fromEntity(savedArticle);
  }

  @Override
  public ArticleDto findById(Integer id) {
    if (id == null) {
      log.error("Article ID is null");
      return null;
    }

    Optional<Article> article = articleRepository.findById(id);
    ArticleDto dto = ArticleDto.fromEntity(article.get());

    return Optional.of(dto)
            .orElseThrow(() ->
                    new EntityNotFoundException("Aucun article avec l'ID = " + id + " n'a ete trouve dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND));
  }

  @Override
  public ArticleDto findByCodeArticle(String codeArticle) {
    if (StringUtils.hasLength(codeArticle)) {
      log.error("Article CODE is null");
      return null;
    }
    Optional<Article> article = articleRepository.findArticleByCodeArticle(codeArticle);
    ArticleDto dto = ArticleDto.fromEntity(article.get());

    return Optional.of(dto).orElseThrow(() -> new EntityNotFoundException("Aucun article avec le CODE = " + codeArticle + " n'a ete trouve dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND));
  }

  @Override
  public List<ArticleDto> findAll() {
    return articleRepository.findAll().stream()
            .map(ArticleDto::fromEntity)
            .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Article ID is null");
      return;
    }

    articleRepository.deleteById(id);
  }
}
