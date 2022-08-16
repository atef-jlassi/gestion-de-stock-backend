package org.commerce.gestiondestock.controller.api;

import io.swagger.annotations.Api;
import org.commerce.gestiondestock.dto.CommandeClientDto;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.commerce.gestiondestock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/commandes-client")
public interface CommandeClientApi {

    ResponseEntity<CommandeClientDto> save(CommandeClientDto dto);

    ResponseEntity<CommandeClientDto> findById(Integer id);

    ResponseEntity<CommandeClientDto> findByCode(String code);
    ResponseEntity<List<CommandeClientDto>> findAll();

    ResponseEntity delete(Integer id);
}
