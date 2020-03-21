package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import blackjack.domain.exceptions.InvalidBettingMoneyException;

class BettingMoneyTest {

	@Test
	void BettingMoney_InputIntBettingMoney_GenerateInstance() {
		assertThat(BettingMoney.valueOf(1000)).isInstanceOf(BettingMoney.class);
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

	@ParameterizedTest
	@NullSource
	void validate_NullBettingMoney_InvalidBettingMoneyExceptionThrown(String value) {
		assertThatThrownBy(() -> BettingMoney.valueOf(value))
			.isInstanceOf(InvalidBettingMoneyException.class)
			.hasMessage(InvalidBettingMoneyException.NULL);
	}

	@Test
	void validateNegative_InputNegativeBettingMoney_InvalidBettingMoneyExceptionThrown() {
		assertThatThrownBy(() -> BettingMoney.valueOf("-1"))
			.isInstanceOf(InvalidBettingMoneyException.class)
			.hasMessage(InvalidBettingMoneyException.INVALID);
	}

	@Test
	void multiplyBy_InputBettingRate_ReturnBettingMoneyOfMultiplied() {
		BettingMoney expected = BettingMoney.valueOf(20000);

		assertThat(BettingMoney.valueOf(10000).multiplyBy(2.0)).isEqualTo(expected);
	}
}
