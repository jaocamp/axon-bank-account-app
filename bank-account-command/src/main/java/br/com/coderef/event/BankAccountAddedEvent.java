package br.com.coderef.event;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class BankAccountAddedEvent {

	private String id;
	private String name;
	private BigDecimal balance;
}