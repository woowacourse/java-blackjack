package com.blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ScoreTest {
	@DisplayName("올바른 범위의 정수를 인자로 넣었을때 인스턴스 생성")
	@ParameterizedTest
	@ValueSource(ints = {0, 21})
	void constructor(int num) {
		assertThat(Score.valueOf(num)).isInstanceOf(Score.class);
	}

	@DisplayName("범위를 벗어난 정수를 인자로 넣었을때 예외 발생")
	@Test
	void constructor_OutOfBoundsInt_ExceptionThrown() {
		assertThatThrownBy(() -> Score.valueOf(-1)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("점수가 인자보다 작은지 여부를 반환")
	@ParameterizedTest
	@CsvSource(value = {"10,true", "11,false"})
	void isLowerThan(int num, boolean expected) {
		Score score = Score.valueOf(num);
		assertThat(score.isLowerThan(11)).isEqualTo(expected);
	}

	@DisplayName("범위를 벗어난 정수를 인자로 넣었을때 예외 발생")
	@Test
	void isBust() {
		Score score = Score.valueOf(0);
		assertThat(score.isBust()).isTrue();
	}

	@DisplayName("점수를 비교")
	@ParameterizedTest
	@CsvSource(value = {"1,0,1", "1,1,0", "0,1,-1"})
	void isBust(int score1, int score2, int expect) {
		Score playerScore = Score.valueOf(score1);
		Score dealerScore = Score.valueOf(score2);
		assertThat(playerScore.compareTo(dealerScore)).isEqualTo(expect);
	}
}
