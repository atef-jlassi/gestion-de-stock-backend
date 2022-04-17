package org.commerce.gestiondestock.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.commerce.gestiondestock.dto.FournisseurDto;
import org.commerce.gestiondestock.exception.EntityNotFoundException;
import org.commerce.gestiondestock.exception.ErrorCodes;
import org.commerce.gestiondestock.exception.InvalidEntityException;
import org.commerce.gestiondestock.exception.InvalidOperationException;
import org.commerce.gestiondestock.models.CommandeClient;
import org.commerce.gestiondestock.models.CommandeFournisseur;
import org.commerce.gestiondestock.repository.CommandeFournisseurRepository;
import org.commerce.gestiondestock.repository.FournisseurRepository;
import org.commerce.gestiondestock.services.FournisseurService;
import org.commerce.gestiondestock.validators.FournisseurValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

  private FournisseurRepository fournisseurRepository;
  private CommandeFournisseurRepository commandeFournisseurRepository;

  @Autowired
  public FournisseurServiceImpl(FournisseurRepository fournisseurRepository,
      CommandeFournisseurRepository commandeFournisseurRepository) {
    this.fournisseurRepository = fournisseurRepository;
    this.commandeFournisseurRepository = commandeFournisseurRepository;
  }


  @Override
  public FournisseurDto save(FournisseurDto fournisseurDto) {
    List<String> errors = FournisseurValidator.validate(fournisseurDto);

    if (!errors.isEmpty()) {
      log.error("Fournisseur is not valid {}", fournisseurDto);
      throw new InvalidEntityException("Le fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
    }

    return FournisseurDto.fromEntity(
            fournisseurRepository.save(
                    FournisseurDto.toEntity(fournisseurDto)
            )
    );
  }

  @Override
  public FournisseurDto findById(Integer id) {
    if (id == null) {
      log.error("Fournisseur ID is null");
      return null;
    }
    return fournisseurRepository.findById(id)
            .map(FournisseurDto::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException(
                    "Aucun fournisseur avec l'ID = " + id + " n' ete trouve dans la BDD",
                    ErrorCodes.FOURNISSEUR_NOT_FOUND)
            );
  }

  @Override
  public List<FournisseurDto> findAll() {
    return fournisseurRepository.findAll().stream()
            .map(FournisseurDto::fromEntity)
            .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Fournisseur ID is null");
      return;
    }
    List<CommandeFournisseur> commandeFournisseur = commandeFournisseurRepository.findAllByFournisseurId(id);
    if (!commandeFournisseur.isEmpty()) {
      throw new InvalidOperationException("Impossible de supprimer un fournisseur qui a deja des commandes",
              ErrorCodes.FOURNISSEUR_ALREADY_IN_USE);
    }
    fournisseurRepository.deleteById(id);
  }

}
