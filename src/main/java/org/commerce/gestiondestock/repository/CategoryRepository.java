package org.commerce.gestiondestock.repository;

import java.util.Optional;
import org.commerce.gestiondestock.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

  Optional<Category> findCategoryByCode(String code);

  Optional<Category> findByIdAndIdEntreprise(Integer id, Integer idEntreprise);

}
