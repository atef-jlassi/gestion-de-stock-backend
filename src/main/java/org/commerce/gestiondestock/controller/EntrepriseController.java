package org.commerce.gestiondestock.controller;

import lombok.AllArgsConstructor;
import org.commerce.gestiondestock.controller.api.EntrepriseApi;
import org.commerce.gestiondestock.dto.EntrepriseDto;
import org.commerce.gestiondestock.services.EntrepriseService;
import org.commerce.gestiondestock.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.commerce.gestiondestock.utils.Constants.ENTREPRISE_ENDPOINT;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EntrepriseController implements EntrepriseApi {

    private EntrepriseService entrepriseService;

    @Override
    @PostMapping(ENTREPRISE_ENDPOINT + "/create")
    public EntrepriseDto save(@RequestBody EntrepriseDto dto) {
        return entrepriseService.save(dto);
    }

    @Override
    @GetMapping(ENTREPRISE_ENDPOINT + "/{id}")
    public EntrepriseDto findById(@PathVariable("id") Integer id) {
        return entrepriseService.findById(id);
    }

    @Override
    @GetMapping(ENTREPRISE_ENDPOINT)
    public List<EntrepriseDto> findAll() {
        return entrepriseService.findAll();
    }

    @Override
    @DeleteMapping(ENTREPRISE_ENDPOINT + "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        entrepriseService.delete(id);
    }
}
