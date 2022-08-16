package org.commerce.gestiondestock.controller;

import lombok.AllArgsConstructor;
import org.commerce.gestiondestock.controller.api.CommandeClientApi;
import org.commerce.gestiondestock.dto.CommandeClientDto;
import org.commerce.gestiondestock.services.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.commerce.gestiondestock.utils.Constants.APP_ROOT;

@RestController
public class CommandeClientController implements CommandeClientApi {

    private CommandeClientService commandeClientService;

    @Autowired
    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    @PostMapping(value = APP_ROOT + "/commandes-client/create")
    public ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto dto) {
    // return ResponseEntity.status(HttpStatus.OK).body(commandeClientService.save(dto));
        return ResponseEntity.ok(commandeClientService.save(dto));
    }

    @Override
    @GetMapping(value = APP_ROOT + "/commandes-client/{id}")
    public ResponseEntity<CommandeClientDto> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(commandeClientService.findById(id));
    }

    @Override
    @GetMapping(value = APP_ROOT + "/commandes-client/byCode/{code}")
    public ResponseEntity<CommandeClientDto> findByCode(@PathVariable("code") String code) {
        return ResponseEntity.ok(commandeClientService.findByCode(code));
    }

    @Override
    @GetMapping(value = APP_ROOT + "/commandes-client")
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return ResponseEntity.ok(commandeClientService.findAll());
    }

    @Override
    @DeleteMapping(value = APP_ROOT + "/commandes-client/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        commandeClientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
