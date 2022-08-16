package org.commerce.gestiondestock.controller.api;
import io.swagger.annotations.Api;
import org.commerce.gestiondestock.dto.UtilisateurDto;
import java.util.List;
import static org.commerce.gestiondestock.utils.Constants.UTILISATEUR_ENDPOINT;

@Api(UTILISATEUR_ENDPOINT)
public interface UtilisateurApi {

    UtilisateurDto save(UtilisateurDto dto);

    UtilisateurDto findById(Integer id);

    List<UtilisateurDto> findAll();

    void delete(Integer id);
}
