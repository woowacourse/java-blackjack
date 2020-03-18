package blackjack.domain.betting;

import blackjack.domain.betting.exceptions.BettingMoneyException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingMoneyTest {

	@Test
	void zero_IsNotNull() {
		assertThat(BettingMoney.zero()).isNotNull();
	}

	@Test
	void zero_IsEqualToZeroInstance() {
		assertThat(BettingMoney.zero()).isEqualTo(BettingMoney.of("0"));
	}

	@ParameterizedTest
	@ValueSource(strings = {"1000", "1000.0", "1000d", "0"})
	void of_IsNotNull(String input) {
		BettingMoney bettingMoney = BettingMoney.of(input);
		assertThat(bettingMoney).isNotNull();
	}

	@ParameterizedTest
	@ValueSource(strings = {"-1000", "-0.01"})
	void of_SameOrLessThanZero_ThrowMoneyException(String input) {
		assertThatThrownBy(() -> BettingMoney.of(input))
				.isInstanceOf(BettingMoneyException.class);
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " ", "abc"})
	void of_NotDouble_ThrowMoneyException(String input) {
		assertThatThrownBy(() -> BettingMoney.of(input))
				.isInstanceOf(BettingMoneyException.class);
	}

	@Test
	void of_Null_ThrowMoneyException() {
		assertThatThrownBy(() -> BettingMoney.of(null))
				.isInstanceOf(BettingMoneyException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"1000,1500", "500,750", "1,1.5"})
	void addWinningAmountWithBlackjack(String input, String expect) {
		BettingMoney bettingMoney = BettingMoney.of(input);
		assertThat(bettingMoney.computeWinningAmountWithBlackjack()).isEqualTo(BettingMoney.of(expect));
	}

	@ParameterizedTest
	@ValueSource(strings = {"1000", "1500", "1500.5", "1.5", "1"})
	void addSimpleWinningAmount() {
		BettingMoney bettingMoney = BettingMoney.of("1000");
		assertThat(bettingMoney.computeSimpleWinningAmount()).isEqualTo(BettingMoney.of("1000"));
	}

	@ParameterizedTest
	@CsvSource(value = {"1000,500,1500", "999.5,0.5,1000", "0,500.5,500.5"})
	void add(String input1, String input2, String expect) {
		BettingMoney a = BettingMoney.of(input1);
		BettingMoney b = BettingMoney.of(input2);
		assertThat(a.add(b)).isEqualTo(BettingMoney.of(expect));
	}
}