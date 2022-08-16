package org.commerce.gestiondestock.controller.api;

import io.swagger.annotations.Api;
import org.commerce.gestiondestock.dto.FournisseurDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.commerce.gestiondestock.utils.Constants.FOURNISSEUR_ENDPOINT;

@Api(FOURNISSEUR_ENDPOINT)
public interface FournisseurApi {

    ResponseEntity<FournisseurDto> save(FournisseurDto dto);

    ResponseEntity<FournisseurDto> findById(Integer id);

    ResponseEntity<List<FournisseurDto>> findAll();

    ResponseEntity delete(Integer id);
}
