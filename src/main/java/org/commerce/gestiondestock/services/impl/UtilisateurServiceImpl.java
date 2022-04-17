package org.commerce.gestiondestock.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.commerce.gestiondestock.dto.ChangerMotDePasseUtilisateurDto;
import org.commerce.gestiondestock.dto.UtilisateurDto;
import org.commerce.gestiondestock.exception.EntityNotFoundException;
import org.commerce.gestiondestock.exception.ErrorCodes;
import org.commerce.gestiondestock.exception.InvalidEntityException;
import org.commerce.gestiondestock.models.Utilisateur;
import org.commerce.gestiondestock.repository.UtilisateurRepository;
import org.commerce.gestiondestock.services.UtilisateurService;
import org.commerce.gestiondestock.validators.UtilisateurValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

  private UtilisateurRepository utilisateurRepository;

  @Autowired
  public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
    this.utilisateurRepository = utilisateurRepository;
  }


  @Override
  public UtilisateurDto save(UtilisateurDto utilisateurDto) {
    List<String> errors = UtilisateurValidator.validate(utilisateurDto);
    if (!errors.isEmpty()) {
      log.error("Utilisateur is not valid {}", utilisateurDto);
      throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
    }

    if(userAlreadyExists(utilisateurDto.getEmail())) {
      throw new InvalidEntityException("Un autre utilisateur avec le meme email existe deja", ErrorCodes.UTILISATEUR_ALREADY_EXISTS,
              Collections.singletonList("Un autre utilisateur avec le meme email existe deja dans la BDD"));
    }


//    utilisateurDto.setMotDePasse(passwordEncoder.encode(utilisateurDto.getMotDePasse()));

    return UtilisateurDto.fromEntity(
            utilisateurRepository.save(
                    UtilisateurDto.toEntity(utilisateurDto)
            )
    );
  }

  private boolean userAlreadyExists(String email) {
    Optional<Utilisateur> user = utilisateurRepository.findUtilisateurByEmail(email);
    return user.isPresent();
  }

  @Override
  public UtilisateurDto findById(Integer id) {
    if (id == null) {
      log.error("Utilisateur ID is null");
      return null;
    }
    return utilisateurRepository.findById(id)
            .map(UtilisateurDto::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException(
                    "Aucun utilisateur avec l'ID = " + id + " n' ete trouve dans la BDD",
                    ErrorCodes.UTILISATEUR_NOT_FOUND)
            );
  }

  @Override
  public List<UtilisateurDto> findAll() {
    return utilisateurRepository.findAll().stream()
            .map(UtilisateurDto::fromEntity)
            .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Utilisateur ID is null");
      return;
    }
    utilisateurRepository.deleteById(id);
  }

}
