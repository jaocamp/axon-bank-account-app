package br.com.coderef.components;

import br.com.coderef.event.BankAddedEvent;
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
public class BankAddedEventProcessor {

    private final BankRepository repository;

    @EventHandler
    public void on(BankAddedEvent event) {
        Bank bank = repository.save(new Bank(event.getId(), event.getName(), event.getBalance()));
        log.info("A bank was added! {}", bank);
    }
}
