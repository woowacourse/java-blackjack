package com.blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ScoreTest {
	@DisplayName("올바른 범위의 정수를 인자로 넣었을때 인스턴스 생성")
	@ParameterizedTest
	@ValueSource(ints = {1, 30})
	void constructor(int num) {
		assertThat(Score.valueOf(num)).isInstanceOf(Score.class);
	}

	@DisplayName("범위를 벗어난 정수를 인자로 넣었을때 예외 발생")
	@ParameterizedTest
	@ValueSource(ints = {0, 31})
	void constructor_OutOfBoundsInt_ExceptionThrown(int num) {
		assertThatThrownBy(() -> Score.valueOf(num)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("점수가 인자보다 작은지 여부를 반환")
	@ParameterizedTest
	@CsvSource(value = {"10,true", "11,false"})
	void isLowerThan(int num, boolean expected) {
		Score score = Score.valueOf(num);
		assertThat(score.isLowerThan(11)).isEqualTo(expected);
	}
}
