package br.com.coderef.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Getter
@ToString
@AllArgsConstructor
public class UpdateBalanceBankAccountCommand {

    @TargetAggregateIdentifier
    private String bankId;
    private BigDecimal balance;
}
