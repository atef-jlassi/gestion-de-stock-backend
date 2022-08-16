package org.commerce.gestiondestock.controller;

import lombok.AllArgsConstructor;
import org.commerce.gestiondestock.controller.api.FournisseurApi;
import org.commerce.gestiondestock.dto.FournisseurDto;
import org.commerce.gestiondestock.services.FournisseurService;
import org.commerce.gestiondestock.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.commerce.gestiondestock.utils.Constants.FOURNISSEUR_ENDPOINT;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FournisseurController implements FournisseurApi {


    private FournisseurService fournisseurService;

    @Override
    @PostMapping(FOURNISSEUR_ENDPOINT + "/create")
    public ResponseEntity<FournisseurDto> save(FournisseurDto dto) {
        return ResponseEntity.ok(fournisseurService.save(dto));
    }

    @Override
    @GetMapping(FOURNISSEUR_ENDPOINT + "/{id}")
    public ResponseEntity<FournisseurDto> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(fournisseurService.findById(id));
    }


    @Override
    @GetMapping(FOURNISSEUR_ENDPOINT)
    public ResponseEntity<List<FournisseurDto>> findAll() {
        return ResponseEntity.ok(fournisseurService.findAll());
    }

    @Override
    @DeleteMapping(FOURNISSEUR_ENDPOINT+"/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        fournisseurService.delete(id);
        return ResponseEntity.ok().build();
    }
}
