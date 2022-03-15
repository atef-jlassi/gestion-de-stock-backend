package org.commerce.gestiondestock.repository;

import java.util.List;
import org.commerce.gestiondestock.models.LigneCommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeFournisseurRepository extends JpaRepository<LigneCommandeFournisseur, Integer> {

  List<LigneCommandeFournisseur> findAllByCommandeFournisseurId(Integer idCommande);
  List<LigneCommandeFournisseur> findAllByArticleId(Integer idCommande);
}
