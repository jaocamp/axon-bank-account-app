package br.com.coderef.components;

import br.com.coderef.event.BankAddedEvent;
import br.com.coderef.event.BankBalanceUpdatedEvent;
import br.com.coderef.event.BankRemovedEvent;
import br.com.coderef.exception.BankNotFoundException;
import br.com.coderef.model.Bank;
import br.com.coderef.repository.BankRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
@ProcessingGroup("amqpEvents")
public class BankEventProcessor {

    private final BankRepository repository;

    @EventHandler
    public void on(BankAddedEvent event) {
        Bank bank = repository.save(new Bank(event.getId(), event.getName(), event.getBalance()));
        log.info("A bank was added! {}", bank);
    }

    @EventHandler
    public void on(BankBalanceUpdatedEvent event) {
        var bank = repository.findById(event.getBankId())
                .orElseThrow(BankNotFoundException::new);
        bank.setBalance(event.getBalance());
        repository.save(bank);
        log.info("A bank balance was updated! {}", bank);
    }

    @EventHandler
    public void on(BankRemovedEvent event) {
        repository.deleteById(event.getId());
        log.info("A bank was removed! {}", event.getId());
    }
}
