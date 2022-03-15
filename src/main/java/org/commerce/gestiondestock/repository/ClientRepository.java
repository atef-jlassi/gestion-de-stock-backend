package org.commerce.gestiondestock.repository;

import org.commerce.gestiondestock.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
