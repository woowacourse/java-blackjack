package domain.money;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 *   class description
 *
 *   @author ParkDooWon, AnHyungJu
 */
public class MoneyTest {
	@Test
	void Should_ThrowException_When_NotInteger() {
		assertThatThrownBy(() -> Money.of("a"))
			.isInstanceOf(NumberFormatException.class);
	}

	@Test
	void Should_ThrowException_When_LessThanMinimum() {
		assertThatThrownBy(() -> Money.of("1000"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("최소");
	}

	@Test
	void Should_ThrowException_When_NotUnitOf10000() {
		assertThatThrownBy(() -> Money.of("15000"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("단위");
	}
}