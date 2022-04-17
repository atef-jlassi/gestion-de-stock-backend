package org.commerce.gestiondestock.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.commerce.gestiondestock.dto.LigneVenteDto;
import org.commerce.gestiondestock.dto.VentesDto;
import org.commerce.gestiondestock.exception.EntityNotFoundException;
import org.commerce.gestiondestock.exception.ErrorCodes;
import org.commerce.gestiondestock.exception.InvalidEntityException;
import org.commerce.gestiondestock.models.Article;
import org.commerce.gestiondestock.models.LigneVente;
import org.commerce.gestiondestock.models.Ventes;
import org.commerce.gestiondestock.repository.ArticleRepository;
import org.commerce.gestiondestock.repository.LigneVenteRepository;
import org.commerce.gestiondestock.repository.VentesRepository;
import org.commerce.gestiondestock.services.VentesService;
import org.commerce.gestiondestock.validators.VentesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VentesServiceImpl implements VentesService {

    private ArticleRepository articleRepository;
    private VentesRepository ventesRepository;
    private LigneVenteRepository ligneVenteRepository;

    @Autowired
    public VentesServiceImpl(ArticleRepository articleRepository,
                             VentesRepository ventesRepository, LigneVenteRepository ligneVenteRepository) {
        this.articleRepository = articleRepository;
        this.ventesRepository = ventesRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public VentesDto save(VentesDto ventesDto) {
        List<String> errors = VentesValidator.validate(ventesDto);
        if (!errors.isEmpty()) {
            log.error("Ventes n'est pas valide");
            throw new InvalidEntityException("L'objet vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        List<String> articleErrors = new ArrayList<>();
        ventesDto.getLigneVente().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
            if (article.isEmpty()) {
                articleErrors.add("Aucun article avec l'ID "+ligneVenteDto.getArticle().getId() + "n'a ete trouve dans la BDD");
            }
        });
        if (!articleErrors.isEmpty()) {
            log.error("One or more articles were not found in the DB, {}", errors);
            throw new InvalidEntityException("Un ou plusieur articles n'ont pas ete trouve dans la BDD ",
                    ErrorCodes.VENTE_NOT_VALID, articleErrors);
        }

        Ventes savedVentes = ventesRepository.save(VentesDto.toEntity(ventesDto));
        ventesDto.getLigneVente().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(savedVentes);
            ligneVenteRepository.save(ligneVente);
        });

        return VentesDto.fromEntity(savedVentes);
    }

    @Override
    public VentesDto findById(Integer id) {
        if (id == null) {
            log.error("Ventes ID is NULL");
            return null;
        }
        return ventesRepository.findById(id).map(VentesDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException("Aucun vente n'a ete trouve dans la BDD", ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public VentesDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Ventes CODE is NULL");
            return null;
        }
        return ventesRepository.findVentesByCode(code).map(VentesDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException("Aucun vente n'a ete trouve dans la BDD", ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream()
                .map(VentesDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Ventes ID is NULL");
        }

        ventesRepository.deleteById(id);
    }
}
