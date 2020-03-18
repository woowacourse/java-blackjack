package blackjack.domain.user;

import blackjack.domain.user.exceptions.MoneyException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingMoneyTest {

	@ParameterizedTest
	@ValueSource(strings = {"1000", "1000.0", "1000d"})
	void of(String input) {
		BettingMoney bettingMoney = BettingMoney.of(input);
		assertThat(bettingMoney).isNotNull();
	}

	@ParameterizedTest
	@ValueSource(strings = {"-1000", "0"})
	void of_SameOrLessThanZero_ThrowMoneyException(String input) {
		assertThatThrownBy(() -> BettingMoney.of(input))
				.isInstanceOf(MoneyException.class);
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " ", "abc"})
	void of_NotDouble_ThrowMoneyException(String input) {
		assertThatThrownBy(() -> BettingMoney.of(input))
				.isInstanceOf(MoneyException.class);
	}

	@Test
	void of_Null_ThrowMoneyException() {
		assertThatThrownBy(() -> BettingMoney.of(null))
				.isInstanceOf(MoneyException.class);
	}


}