package org.commerce.gestiondestock.controller;

import lombok.AllArgsConstructor;
import org.commerce.gestiondestock.controller.api.VentesApi;
import org.commerce.gestiondestock.dto.VentesDto;
import static org.commerce.gestiondestock.utils.Constants.*;

import org.commerce.gestiondestock.services.VentesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class VentesController implements VentesApi {

    private VentesService ventesService;

    @Override
    @PostMapping(VENTES_ENDPOINT+"/create")
    public VentesDto save(@RequestBody VentesDto dto) {
        return ventesService.save(dto);
    }

    @Override
    @GetMapping(VENTES_ENDPOINT+ "/{id}")
    public VentesDto findById(@PathVariable("id") Integer id) {
        return ventesService.findById(id);
    }

    @Override
    @GetMapping(VENTES_ENDPOINT+"/byCode/{code}")
    public VentesDto findByCode(@PathVariable("code") String code) {
        return ventesService.findByCode(code);
    }

    @Override
    @GetMapping(VENTES_ENDPOINT)
    public List<VentesDto> findAll() {
        return ventesService.findAll();
    }

    @Override
    @DeleteMapping(VENTES_ENDPOINT+ "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        ventesService.delete(id);
    }
}
