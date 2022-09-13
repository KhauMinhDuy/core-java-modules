package com.khauminhduy.constructor;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class BankAccount {

	protected String name;
	protected LocalDateTime opened;
	protected double balance;

	@Override
	public String toString() {
		return String.format("%s, %s, %f", this.name, this.opened.toString(), this.balance);
	}

}

class BankAccountEmptyConstructor extends BankAccount {
	public BankAccountEmptyConstructor() {
		this.name = "";
		this.opened = LocalDateTime.now();
		this.balance = 0.0d;
	}
}

class BankAccountParameterizedConstructor extends BankAccount {
	public BankAccountParameterizedConstructor(String name, LocalDateTime opened, double balance) {
		this.name = name;
		this.opened = opened;
		this.balance = balance;
	}
}

class BankAccountCopyConstructor extends BankAccount {
	public BankAccountCopyConstructor(String name, LocalDateTime opened, double balance) {
		this.name = name;
		this.opened = opened;
		this.balance = balance;
	}

	public BankAccountCopyConstructor(BankAccount other) {
		this.name = other.name;
		this.opened = LocalDateTime.now();
		this.balance = 0.0f;
	}
}

class BankAccountChainedConstructors extends BankAccount {
	public BankAccountChainedConstructors(String name, LocalDateTime opened, double balance) {
		this.name = name;
		this.opened = opened;
		this.balance = balance;
	}

	public BankAccountChainedConstructors(String name) {
		this(name, LocalDateTime.now(), 0.0f);
	}
}
