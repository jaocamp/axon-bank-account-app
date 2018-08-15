package br.com.coderef.controller;

import br.com.coderef.command.AddBankCommand;
import br.com.coderef.dto.BankDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Slf4j
@AllArgsConstructor
@RestController("/banks")
public class BankController {

    private CommandGateway commandGateway;

    @PostMapping
    public CompletableFuture<String> addProductToCatalog(@RequestBody BankDTO dto) {
        UUID id = UUID.randomUUID();
        AddBankCommand command = new AddBankCommand(id.toString(), dto.getName(), dto.getBalance());
        log.info("Executing command: {}", command);
        return commandGateway.send(command);
    }
}