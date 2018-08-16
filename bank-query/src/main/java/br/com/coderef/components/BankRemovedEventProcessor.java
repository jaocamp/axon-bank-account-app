package br.com.coderef.components;

import br.com.coderef.event.BankRemovedEvent;
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
public class BankRemovedEventProcessor {

    private final BankRepository repository;

    @EventHandler
    public void on(BankRemovedEvent event) {
        repository.deleteById(event.getId());
        log.info("A bank was removed! {}", event.getId());
    }
}
