package org.commerce.gestiondestock.repository;

import org.commerce.gestiondestock.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {


    Optional<Utilisateur> findUtilisateurByEmail(String email);
}
