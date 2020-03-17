package domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class YesOrNoTest {
	@Test
	void Should_ThrowException_When_NotYOrN() {
		assertThatThrownBy(() -> YesOrNo.of("t"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("y또는 n");
	}

	@Test
	void Should_YesOrNoClass_When_YOrN() {
		assertThat(YesOrNo.of("y")).isEqualTo(YesOrNo.YES);
		assertThat(YesOrNo.of("n")).isEqualTo(YesOrNo.NO);
	}

	@Test
	void isYes_True_When_Y() {
		assertTrue(YesOrNo.of("y").isYes());
		assertFalse(YesOrNo.of("n").isYes());
	}
}
