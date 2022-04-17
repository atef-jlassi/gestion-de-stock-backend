package org.commerce.gestiondestock.services;


import org.commerce.gestiondestock.dto.ChangerMotDePasseUtilisateurDto;
import org.commerce.gestiondestock.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

  UtilisateurDto save(UtilisateurDto dto);

  UtilisateurDto findById(Integer id);

  List<UtilisateurDto> findAll();

  void delete(Integer id);


}
