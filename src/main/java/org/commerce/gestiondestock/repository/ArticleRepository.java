package org.commerce.gestiondestock.repository;

import java.util.List;
import java.util.Optional;
import org.commerce.gestiondestock.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

  Optional<Article> findArticleByCodeArticle(String codeArticle);

  List<Article> findAllByCategoryId(Integer idCategory);

}
