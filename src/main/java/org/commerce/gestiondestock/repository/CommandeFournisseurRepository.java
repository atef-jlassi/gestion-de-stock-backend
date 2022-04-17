package org.commerce.gestiondestock.repository;

import java.util.List;
import java.util.Optional;
import org.commerce.gestiondestock.models.CommandeClient;
import org.commerce.gestiondestock.models.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {

  Optional<CommandeFournisseur> findCommandeFournisseurByCode(String code);

  List<CommandeFournisseur> findAllByFournisseurId(Integer id);
}
