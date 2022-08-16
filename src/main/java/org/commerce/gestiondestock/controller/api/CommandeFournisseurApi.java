package org.commerce.gestiondestock.controller.api;

import io.swagger.annotations.Api;
import org.commerce.gestiondestock.dto.CommandeFournisseurDto;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.commerce.gestiondestock.utils.Constants.COMMANDE_FOURNISSEUR_ENDPOINT;

@Api(COMMANDE_FOURNISSEUR_ENDPOINT)
public interface CommandeFournisseurApi {

    ResponseEntity<CommandeFournisseurDto> save(CommandeFournisseurDto dto);

    ResponseEntity<CommandeFournisseurDto> findById(Integer id);

    ResponseEntity<CommandeFournisseurDto> findByCode(String code);

    ResponseEntity<List<CommandeFournisseurDto>> findAll();

    ResponseEntity delete(Integer id);
}
