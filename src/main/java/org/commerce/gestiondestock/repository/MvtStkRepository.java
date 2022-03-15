package org.commerce.gestiondestock.repository;

import java.util.List;
import org.commerce.gestiondestock.models.MvtStk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MvtStkRepository extends JpaRepository<MvtStk, Integer> {

  List<MvtStk> findAllByArticleId(Integer idArticle);

}
