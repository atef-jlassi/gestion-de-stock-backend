package org.commerce.gestiondestock.repository;

import java.util.Optional;
import org.commerce.gestiondestock.models.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentesRepository extends JpaRepository<Ventes, Integer> {

  Optional<Ventes> findVentesByCode(String code);
}
