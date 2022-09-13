package com.khauminhduy.constructor;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.Month;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ConstructorUnitTest {

	@Test
	public void givenNoExplicitContructor_whenUsed_thenFails() {
		BankAccount bankAccount = new BankAccount();
		Assertions.assertThatThrownBy(() -> {
			bankAccount.toString();
		}).isInstanceOf(Exception.class);
	}

	@Test
	public void givenNoArgumentConstructor_whenUsed_thenSucceeds() {
		BankAccountEmptyConstructor bankAccountEmptyConstructor = new BankAccountEmptyConstructor();
		Assertions.assertThatCode(() -> bankAccountEmptyConstructor.toString()).doesNotThrowAnyException();
	}

	@Test
	public void givenParameterisedConstructor_whenUsed_thenSucceeds() {
		LocalDateTime opened = LocalDateTime.of(2022, Month.SEPTEMBER, 13, 10, 0, 0);
		BankAccountParameterizedConstructor account = new BankAccountParameterizedConstructor("Tom", opened, 1000.0f);
		Assertions.assertThatCode(() -> account.toString()).doesNotThrowAnyException();
	}

	@Test
	public void givenCopyContructor_whenUser_thenMaintainsLogic() {
		LocalDateTime opened = LocalDateTime.of(2022, Month.SEPTEMBER, 13, 10, 0, 0);
		BankAccountCopyConstructor account = new BankAccountCopyConstructor("Tim", opened, 1000.0f);
		BankAccountCopyConstructor newAccount = new BankAccountCopyConstructor(account);

		assertThat(account.getName()).isEqualTo(newAccount.getName());
		assertThat(account.getOpened()).isNotEqualTo(newAccount.getOpened());
		assertThat(account.getBalance()).isNotEqualTo(newAccount.getBalance());
	}

	@Test
	public void givenChainedConstructor_whenUsed_thenMaintainsLogic() {
		BankAccountChainedConstructors account = new BankAccountChainedConstructors("Tim");
		BankAccountChainedConstructors newAccount = new BankAccountChainedConstructors("Tim", LocalDateTime.now(), 0.0f);

		assertThat(account.getName()).isEqualTo(newAccount.getName());
		assertThat(account.getBalance()).isEqualTo(newAccount.getBalance());
	}

}
