package blackjack.domain.participant;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {
	private Money money;

	@BeforeEach
	void setUp() {
		money = Money.of(1000);
	}

	@Test
	@DisplayName("돈 객체 생성 확인")
	void create() {
		assertEquals(money, Money.of(1000));
	}

	@Test
	@DisplayName("돈 계산")
	void calculateMoney() {
		assertEquals(money.multiplyMoneyWithOperation(-1), -1000);
	}
}
