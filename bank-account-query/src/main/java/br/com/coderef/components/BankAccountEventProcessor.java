package br.com.coderef.components;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import br.com.coderef.event.BankAccountAddedEvent;
import br.com.coderef.event.BankAccountBalanceUpdatedEvent;
import br.com.coderef.event.BankAccountRemovedEvent;
import br.com.coderef.exception.BankAccountNotFoundException;
import br.com.coderef.model.BankAccount;
import br.com.coderef.repository.BankAccountRepository;

@Slf4j
@Component
@AllArgsConstructor
@ProcessingGroup("amqpEvents")
public class BankAccountEventProcessor {

    private final BankAccountRepository repository;

    @EventHandler
    public void on(BankAccountAddedEvent event) {
        BankAccount bankAccount = repository.save(new BankAccount(event.getId(), event.getName(), event.getBalance()));
        log.info("A bank account was added! {}", bankAccount );
    }

    @EventHandler
    public void on(BankAccountBalanceUpdatedEvent event) {
        var bank = repository.findById(event.getBankId())
                .orElseThrow(BankAccountNotFoundException::new);
        bank.setBalance(event.getBalance());
        repository.save(bank);
        log.info("A bank account balance was updated! {}", bank);
    }

    @EventHandler
    public void on(BankAccountRemovedEvent event) {
        repository.deleteById(event.getId());
        log.info("A bank account was removed! {}", event.getId());
    }
}
