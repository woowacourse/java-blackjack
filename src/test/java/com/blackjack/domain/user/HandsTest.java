package com.blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.blackjack.domain.Score;

class HandsTest {
	@DisplayName("빈 리스트를 인자로 넣었을때 인스턴스 생성")
	@Test
	void constructor() {
		assertThat(new Hands(Collections.emptyList())).isInstanceOf(Hands.class);
	}

	@DisplayName("모든 패를 확인하여 점수 생성")
	@Test
	void calculateScore() {
		Hands hands = new Hands(Collections.emptyList());
		assertThat(hands.calculateScore()).isEqualTo(new Score(0));
	}
}
