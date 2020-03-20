package com.blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BettingMoneyTest {
	@DisplayName("베팅액을 입력받아 인스턴스 생성")
	@ParameterizedTest
	@ValueSource(ints = {1_000, 200_000_000})
	void constructor(int inputMoney) {
		assertThat(new BettingMoney(inputMoney)).isInstanceOf(BettingMoney.class);
	}

	@DisplayName("범위를 벗어난 베팅액을 입력받았을 때 예외 발생")
	@ParameterizedTest
	@ValueSource(ints = {0, 200_001_000})
	void constructor_InvalidBounds_ExceptionThrown(int inputMoney) {
		assertThatThrownBy(() -> new BettingMoney(inputMoney)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("1000 단위로 나누어 떨어지지 않는 베팅액을 입력받았을 때 예외 발생")
	@Test
	void constructor_InvalidUnit_ExceptionThrown() {
		assertThatThrownBy(() -> new BettingMoney(1100)).isInstanceOf(IllegalArgumentException.class);
	}
}
