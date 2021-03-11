package blackjack.domain.participant;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProfitTest {
	private Profit profit;

	@BeforeEach
	void setUp() {
		profit = new Profit(1000);
	}

	@Test
	void calculateMoney() {
		assertEquals(profit.calculateMoneyWithProfit(-1), -1000);
	}
}
