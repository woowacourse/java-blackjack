package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import blackjack.domain.exceptions.InvalidBettingMoneyException;

class BettingMoneyTest {

	@Test
	void BettingMoney_InputIntBettingMoney_GenerateInstance() {
		assertThat(new BettingMoney(1000)).isInstanceOf(BettingMoney.class);
	}

	@Test
	void validate_InputNegativeBettingMoney_InvalidBettingMoneyException() {
		assertThatThrownBy(() -> new BettingMoney(-1))
			.isInstanceOf(InvalidBettingMoneyException.class)
			.hasMessage(InvalidBettingMoneyException.INVALID);
	}

	@Test
	void valueOf_InputStringBettingMoney_GenerateInstance() {
		assertThat(BettingMoney.valueOf("1000")).isInstanceOf(BettingMoney.class);
	}

	@Test
	void valueOf_InvalidStringBettingMoney_GenerateInstance() {
		assertThatThrownBy(() -> BettingMoney.valueOf("abc"))
			.isInstanceOf(InvalidBettingMoneyException.class)
			.hasMessage(InvalidBettingMoneyException.NOT_INTEGER);
	}

	@Test
	void multiply_InputBettingRate_ReturnBettingMoneyOfMultiplied() {
		BettingMoney expected = new BettingMoney(20000);

		assertThat(new BettingMoney(10000).multiply(2.0)).isEqualTo(expected);
	}
}
