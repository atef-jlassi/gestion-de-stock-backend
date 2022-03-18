package org.commerce.gestiondestock.repository;

import java.util.List;
import org.commerce.gestiondestock.models.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneVenteRepository extends JpaRepository<LigneVente, Integer> {

}
