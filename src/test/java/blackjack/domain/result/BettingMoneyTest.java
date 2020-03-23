package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import blackjack.domain.exceptions.InvalidBettingMoneyException;

class BettingMoneyTest {

	@Test
	void BettingMoney_InputIntBettingMoney_GenerateInstance() {
		assertThat(new BettingMoney(1000)).isInstanceOf(BettingMoney.class);
	}

	@Test
	void valueOf_InputStringBettingMoney_GenerateInstance() {
		assertThat(new BettingMoney("1000")).isInstanceOf(BettingMoney.class);
	}

	@Test
	void valueOf_InvalidStringBettingMoney_GenerateInstance() {
		assertThatThrownBy(() -> new BettingMoney("abc"))
			.isInstanceOf(InvalidBettingMoneyException.class)
			.hasMessage(InvalidBettingMoneyException.NOT_INTEGER);
	}

	@ParameterizedTest
	@NullSource
	void validate_NullBettingMoney_InvalidBettingMoneyExceptionThrown(String value) {
		assertThatThrownBy(() -> new BettingMoney(value))
			.isInstanceOf(InvalidBettingMoneyException.class)
			.hasMessage(InvalidBettingMoneyException.NULL);
	}

	@Test
	void validateNegative_InputNegativeBettingMoney_InvalidBettingMoneyExceptionThrown() {
		assertThatThrownBy(() -> new BettingMoney("-1"))
			.isInstanceOf(InvalidBettingMoneyException.class)
			.hasMessage(InvalidBettingMoneyException.INVALID);
	}

	@Test
	void multiplyBy_InputBettingRate_ReturnBettingMoneyOfMultiplied() {
		int expected = 20000;

		assertThat(new BettingMoney(10000).multiplyBy(2.0)).isEqualTo(expected);
	}
}
