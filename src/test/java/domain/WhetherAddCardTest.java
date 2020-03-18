package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class WhetherAddCardTest {
	@Test
	void of() {
		assertThat(WhetherAddCard.of("y")).isEqualTo(WhetherAddCard.YES);
		assertThat(WhetherAddCard.of("n")).isEqualTo(WhetherAddCard.NO);
		assertThat(WhetherAddCard.of("Y")).isEqualTo(WhetherAddCard.YES);
		assertThat(WhetherAddCard.of("N")).isEqualTo(WhetherAddCard.NO);
	}

	@Test
	@SuppressWarnings("NonAsciiCharacters")
	void of_y_또는_n이_아닌_경우() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> WhetherAddCard.of("a"));
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> WhetherAddCard.of("b"));
	}

	@Test
	void isYes() {
		assertThat(WhetherAddCard.of("y").isYes()).isTrue();
		assertThat(WhetherAddCard.of("n").isYes()).isFalse();
		assertThat(WhetherAddCard.of("Y").isYes()).isTrue();
		assertThat(WhetherAddCard.of("N").isYes()).isFalse();
	}
}
