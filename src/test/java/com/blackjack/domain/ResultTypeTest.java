package com.blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ResultTypeTest {
	@Test
	void of_GivenPositiveValue_ReturnWin() {
		assertThat(ResultType.of(new Score(5), new Score(0))).isEqualTo(ResultType.WIN);
	}

	@Test
	void of_GivenPositiveValue_ReturnDraw() {
		assertThat(ResultType.of(new Score(5), new Score(5))).isEqualTo(ResultType.DRAW);
	}

	@Test
	void of_GivenPositiveValue_ReturnLose() {
		assertThat(ResultType.of(new Score(0), new Score(5))).isEqualTo(ResultType.LOSE);
	}
}
