package br.com.coderef.aggregate;

import br.com.coderef.command.AddBankCommand;
import br.com.coderef.event.BankAddedEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.math.BigDecimal;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Slf4j
@Getter
@Aggregate
public class BankAggregate {

    @AggregateIdentifier
    private String id;
    private String name;
    private BigDecimal balance;

    @CommandHandler
    public BankAggregate(AddBankCommand cmd) {
        log.debug("Handling {} command: {}", cmd.getClass().getSimpleName(), cmd);
        Assert.hasLength(cmd.getId(), "Id should not be empty or null.");
        Assert.hasLength(cmd.getName(), "Name should not be empty or null.");
        Assert.notNull(cmd.getBalance(), "Balance should not be empty or null.");

        apply(new BankAddedEvent(cmd.getId(), cmd.getName(), cmd.getBalance()));
        log.trace("Done handling {} command: {}", cmd.getClass().getSimpleName(), cmd);
    }

    @EventSourcingHandler
    public void on(BankAddedEvent event) {
        log.debug("Handling {} event: {}", event.getClass().getSimpleName(), event);
        this.id = event.getId();
        this.name = event.getName();
        this.balance = event.getBalance();
        log.trace("Done handling {} event: {}", event.getClass().getSimpleName(), event);
    }
}