package org.commerce.gestiondestock.controller;

import lombok.AllArgsConstructor;
import org.commerce.gestiondestock.controller.api.UtilisateurApi;
import org.commerce.gestiondestock.dto.UtilisateurDto;
import org.commerce.gestiondestock.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.commerce.gestiondestock.utils.Constants.UTILISATEUR_ENDPOINT;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UtilisateurController implements UtilisateurApi {

    private UtilisateurService utilisateurService;

    @Override
    @PostMapping(UTILISATEUR_ENDPOINT + "/create")
    public UtilisateurDto save(@RequestBody UtilisateurDto dto) {
        return utilisateurService.save(dto);
    }

    @Override
    @GetMapping(UTILISATEUR_ENDPOINT+"/{id}")
    public UtilisateurDto findById(@PathVariable("id") Integer id) {
        return utilisateurService.findById(id);
    }

    @Override
    @GetMapping(UTILISATEUR_ENDPOINT)
    public List<UtilisateurDto> findAll() {
        return utilisateurService.findAll();
    }

    @Override
    @DeleteMapping(UTILISATEUR_ENDPOINT+"/{id}")
    public void delete(@PathVariable("id") Integer id) {
        utilisateurService.delete(id);
    }
}
