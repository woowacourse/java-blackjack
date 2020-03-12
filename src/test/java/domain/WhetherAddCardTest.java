package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class WhetherAddCardTest {
	@Test
	void of() {
		assertThat(WhetherAddCard.of("y")).isEqualTo(WhetherAddCard.YES);
		assertThat(WhetherAddCard.of("n")).isEqualTo(WhetherAddCard.NO);
	}

	@Test
	void of_y_또는_n이_아닌_경우() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> WhetherAddCard.of("a"));
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> WhetherAddCard.of("b"));
	}
}
