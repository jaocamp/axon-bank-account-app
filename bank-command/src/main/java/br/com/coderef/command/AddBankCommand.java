package br.com.coderef.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@AllArgsConstructor
public class AddBankCommand {

	private String id;
	private String name;
	private BigDecimal balance;
}