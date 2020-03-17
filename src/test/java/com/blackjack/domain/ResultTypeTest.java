package com.blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ResultTypeTest {
	@Test
	void of_GivenPositiveValue_ReturnWin() {
		assertThat(ResultType.of(1)).isEqualTo(ResultType.WIN);
	}

	@Test
	void of_GivenPositiveValue_ReturnDraw() {
		assertThat(ResultType.of(0)).isEqualTo(ResultType.DRAW);
	}

	@Test
	void of_GivenPositiveValue_ReturnLose() {
		assertThat(ResultType.of(-1)).isEqualTo(ResultType.LOSE);
	}
}
