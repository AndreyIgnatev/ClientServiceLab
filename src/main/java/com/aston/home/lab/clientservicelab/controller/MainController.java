package com.aston.home.lab.clientservicelab.controller;

import com.aston.home.lab.clientservicelab.dto.ClientDTO;
import com.aston.home.lab.clientservicelab.entity.Client;
import com.aston.home.lab.clientservicelab.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/inDocker/api/main")
@RequiredArgsConstructor
public class MainController {

    private final ClientRepository clientRepository;

    @GetMapping("/client/get/id")
    public ResponseEntity<Client> getClientById(@RequestParam UUID id) {
        Client client = clientRepository.findById(id).orElse(null);
        return new ResponseEntity<>(client, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/client/get/phone")
    public ResponseEntity<Client> getClientByPhoneNumber(@RequestParam String phoneNumber) {
        Client client = clientRepository.findByPhoneNumber(phoneNumber);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        return new ResponseEntity<>(client, HttpStatus.valueOf(200));
    }

    @PostMapping("/client/new")
    public ResponseEntity<Client> addNewClient(@RequestBody ClientDTO clientDTO) {
        if (clientDTO.phoneNumber() == null || clientDTO.phoneNumber().isBlank()) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        Client newClient = Client.builder()
                .address(clientDTO.address())
                .phoneNumber(clientDTO.phoneNumber())
                .firstName(clientDTO.firstName())
                .secondName(clientDTO.secondName())
                .build();

        return new ResponseEntity<>(clientRepository.save(newClient), HttpStatusCode.valueOf(201));
    }
}