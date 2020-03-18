package com.blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerFactoryTest {
	@DisplayName("인자로 중복되는 이름을 넣었을 때 예외 발생")
	@Test
	void createPlayers_DuplicatedName_ExceptionThrown() {
		String names = "pobi, pobi, stitch, ttoring";
		assertThatThrownBy(() -> PlayerFactory.createPlayers(names)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("참여자의 수가 범위를 벗어나는 경우 예외 발생")
	@Test
	void createPlayers_OutOfBounds_ExceptionThrown() {
		String names = "1,2,3,4,5,6,7,8";
		assertThatThrownBy(() -> PlayerFactory.createPlayers(names)).isInstanceOf(IllegalArgumentException.class);
	}
}
