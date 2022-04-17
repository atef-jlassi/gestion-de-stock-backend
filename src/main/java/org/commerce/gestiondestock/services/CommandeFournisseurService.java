package org.commerce.gestiondestock.services;


import org.commerce.gestiondestock.dto.CommandeFournisseurDto;
import org.commerce.gestiondestock.dto.LigneCommandeFournisseurDto;
import org.commerce.gestiondestock.enums.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeFournisseurService {

  CommandeFournisseurDto save(CommandeFournisseurDto dto);

  CommandeFournisseurDto findById(Integer id);

  CommandeFournisseurDto findByCode(String code);

  List<CommandeFournisseurDto> findAll();

  void delete(Integer id);

}
