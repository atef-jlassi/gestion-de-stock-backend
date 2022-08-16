package org.commerce.gestiondestock.controller.api;

import io.swagger.annotations.Api;
import org.commerce.gestiondestock.dto.EntrepriseDto;
import java.util.List;

import static org.commerce.gestiondestock.utils.Constants.ENTREPRISE_ENDPOINT;

@Api(ENTREPRISE_ENDPOINT)
public interface EntrepriseApi {

    EntrepriseDto save(EntrepriseDto dto);

    EntrepriseDto findById(Integer id);

    List<EntrepriseDto> findAll();

    void delete(Integer id);
}
