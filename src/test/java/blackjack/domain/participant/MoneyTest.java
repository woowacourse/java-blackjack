package blackjack.domain.participant;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoneyTest {
	private Money money;

	@BeforeEach
	void setUp() {
		money = new Money(1000);
	}

	@Test
	void calculateMoney() {
		assertEquals(money.calculateMoneyWithProfit(-1), -1000);
	}
}
