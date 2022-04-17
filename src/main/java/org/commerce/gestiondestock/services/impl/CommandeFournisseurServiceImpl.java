package org.commerce.gestiondestock.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.commerce.gestiondestock.dto.CommandeClientDto;
import org.commerce.gestiondestock.dto.CommandeFournisseurDto;
import org.commerce.gestiondestock.dto.LigneCommandeFournisseurDto;
import org.commerce.gestiondestock.exception.EntityNotFoundException;
import org.commerce.gestiondestock.exception.ErrorCodes;
import org.commerce.gestiondestock.exception.InvalidEntityException;
import org.commerce.gestiondestock.models.*;
import org.commerce.gestiondestock.repository.ArticleRepository;
import org.commerce.gestiondestock.repository.CommandeFournisseurRepository;
import org.commerce.gestiondestock.repository.FournisseurRepository;
import org.commerce.gestiondestock.repository.LigneCommandeFournisseurRepository;
import org.commerce.gestiondestock.services.CommandeFournisseurService;
import org.commerce.gestiondestock.validators.CommandeFournisseurValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFournisseurRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository,
                                          LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository,
                                          FournisseurRepository fournisseurRepository, ArticleRepository articleRepository) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        List<String> errors = CommandeFournisseurValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Commande fournisseur n'est pas valide");
            throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID);
        }
        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(dto.getFournisseur().getId());
        if (fournisseur.isEmpty()) {
            log.warn("Fournisseur with ID {} was not found in the DB", dto.getFournisseur().getId());
            throw new EntityNotFoundException("Aucun founrisseur avec l'ID "+
                    dto.getFournisseur().getId()+ " n'a été trouve dans la BDD",ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }
        List<String> articlesErrors = new ArrayList<>();
        if (dto.getLigneCommandeFournisseurs() != null) {
            dto.getLigneCommandeFournisseurs().forEach(ligneCmdFournisseur -> {
                if (ligneCmdFournisseur.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(ligneCmdFournisseur.getArticle().getId());
                    if (article.isEmpty()) {
                        articlesErrors.add("L'article avec l'ID "+ ligneCmdFournisseur.getArticle().getCodeArticle() + " n'existe pas");
                    }
                } else {
                    articlesErrors.add("Impossible d'enregistrer une commande avec un article NULL");
                }
            });
        }
        if (!articlesErrors.isEmpty()) {
            log.warn("");
            throw new InvalidEntityException("Article n'existe pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articlesErrors);
        }
        CommandeFournisseur cmdFournisseur = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(dto));

        if (dto.getLigneCommandeFournisseurs() != null) {
            dto.getLigneCommandeFournisseurs().forEach(ligneCmdFournisseur -> {
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligneCmdFournisseur);
                ligneCommandeFournisseur.setCommandeFournisseur(cmdFournisseur);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }
        return CommandeFournisseurDto.fromEntity(cmdFournisseur);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if (id == null) {
            log.error("Commande Fournisseur ID is NULL");
            return null;
        }
        return commandeFournisseurRepository.findById(id).map(CommandeFournisseurDto::fromEntity)
                                        .orElseThrow(() -> new EntityNotFoundException("Aucune commande client n'a ete trouve avec l'ID "+ id,
                                                ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Commande fournisseur CODE is NULL");
            return null;
        }
        return commandeFournisseurRepository.findCommandeFournisseurByCode(code).map(CommandeFournisseurDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException("Aucune commande fournisseur n'a ete trouve avec le CODE "+ code, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream().map(CommandeFournisseurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Commande Fournisseur ID is NULL");
        }
        commandeFournisseurRepository.deleteById(id);
    }
}
