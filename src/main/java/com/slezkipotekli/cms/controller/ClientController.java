package com.slezkipotekli.cms.controller;

import com.slezkipotekli.cms.entity.Client;
import com.slezkipotekli.cms.faker.ClientFaker;
import com.slezkipotekli.cms.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author vregi, 12/13/2024
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client")
@Slf4j
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Integer id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    @PostMapping("/faker")
    public List<Client> createClientsByFaker() {
        List<Client> clients = Stream
                .generate(ClientFaker::createFakeClient)
                .limit(40)
                .toList();
        return clientService.createDepartmentsByFaker(clients);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Integer id, @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Integer id) {
        clientService.deleteClient(id);
    }
}
