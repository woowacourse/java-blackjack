package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BettingMoneyTest {
	@Test
	void constructor() {
		assertThat(new BettingMoney(1000)).isInstanceOf(BettingMoney.class);
	}

	@Test
	void constructor_0미만() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new BettingMoney(-1));
	}

	@Test
	void from_문자열() {
		assertThat(BettingMoney.from("1000")).isInstanceOf(BettingMoney.class);
	}

	@Test
	void getValue() {
		assertThat(new BettingMoney(500).getValue()).isEqualTo(500);
	}
}
