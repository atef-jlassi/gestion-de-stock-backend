package org.commerce.gestiondestock.controller.api;

import io.swagger.annotations.Api;
import org.commerce.gestiondestock.dto.VentesDto;
import static org.commerce.gestiondestock.utils.Constants.*;

import java.util.List;

@Api(VENTES_ENDPOINT)
public interface VentesApi {

    VentesDto save(VentesDto dto);

    VentesDto findById(Integer id);

    VentesDto findByCode(String code);

    List<VentesDto> findAll();

    void delete(Integer id);
}
