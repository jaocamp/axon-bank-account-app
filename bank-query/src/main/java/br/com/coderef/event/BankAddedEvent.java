package br.com.coderef.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@AllArgsConstructor
public class BankAddedEvent {

	private String id;
	private String name;
	private BigDecimal balance;
}