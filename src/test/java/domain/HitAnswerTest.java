package domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class HitAnswerTest {
	@Test
	void Should_ThrowException_When_NotYOrN() {
		assertThatThrownBy(() -> HitAnswer.of("t"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("y또는 n");
	}

	@Test
	void Should_HitAnswerClass_When_YOrN() {
		assertThat(HitAnswer.of("y")).isEqualTo(HitAnswer.YES);
		assertThat(HitAnswer.of("n")).isEqualTo(HitAnswer.NO);
	}

	@Test
	void isYes_True_When_Y() {
		assertTrue(HitAnswer.of("y").isYes());
		assertFalse(HitAnswer.of("n").isYes());
	}
}
