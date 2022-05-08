package org.commerce.gestiondestock.controller;

import org.commerce.gestiondestock.controller.api.ApiClient;
import org.commerce.gestiondestock.dto.ClientDto;
import org.commerce.gestiondestock.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.commerce.gestiondestock.utils.Constants.APP_ROOT;

@RestController
public class ClientController implements ApiClient {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    @PostMapping(value = APP_ROOT + "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientDto save(@RequestBody ClientDto dto) {
        return clientService.save(dto);
    }

    @Override
    @GetMapping(value = APP_ROOT+ "/clients/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientDto findById(@PathVariable("id") Integer id) {
        return clientService.findById(id);
    }

    @Override
    @GetMapping(value = APP_ROOT+ "/clients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @Override
    @DeleteMapping(value = APP_ROOT + "/clients/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        clientService.delete(id);
    }
}
