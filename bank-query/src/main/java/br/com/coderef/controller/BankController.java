package br.com.coderef.controller;

import br.com.coderef.model.Bank;
import br.com.coderef.repository.BankRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/banks")
public class BankController {

    private BankRepository repository;

    @GetMapping
    public ResponseEntity<Iterable<Bank>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }
}