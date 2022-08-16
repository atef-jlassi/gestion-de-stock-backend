package org.commerce.gestiondestock.controller;

import lombok.AllArgsConstructor;
import org.commerce.gestiondestock.controller.api.CommandeFournisseurApi;
import org.commerce.gestiondestock.dto.CommandeFournisseurDto;
import org.commerce.gestiondestock.services.CommandeFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.commerce.gestiondestock.utils.Constants.*;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommandeFournisseurController implements CommandeFournisseurApi {

  private CommandeFournisseurService commandeFournisseurService;

  @Override
  @PostMapping(CRAETE_COMMANDE_FOURNISSEUR_ENDPOINT)
  public ResponseEntity<CommandeFournisseurDto> save(@RequestBody CommandeFournisseurDto dto) {
    return ResponseEntity.ok(commandeFournisseurService.save(dto));
  }

  @Override
  @GetMapping(FIND_COMMANDE_FOURNISSEUR_BY_ID_ENDPOINT)
  public ResponseEntity<CommandeFournisseurDto> findById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(commandeFournisseurService.findById(id));
  }

  @Override
  @GetMapping(FIND_COMMANDE_FOURNISSEUR_BY_CODE_ENDPOINT)
  public ResponseEntity<CommandeFournisseurDto> findByCode(@PathVariable("code") String code) {
    return ResponseEntity.ok(commandeFournisseurService.findByCode(code));
  }

  @Override
  @GetMapping(FIND_ALL_COMMANDE_FOURNISSEUR_ENDPOINT)
  public ResponseEntity<List<CommandeFournisseurDto>> findAll() {
    return ResponseEntity.ok(commandeFournisseurService.findAll());
  }

  @Override
  @DeleteMapping(DELETE_COMMANDE_FOURNISSEUR_ENDPOINT)
  public ResponseEntity delete(@PathVariable("id") Integer id) {
    commandeFournisseurService.delete(id);
    return ResponseEntity.ok().build();
  }
}
