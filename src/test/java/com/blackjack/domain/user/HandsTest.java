package com.blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.blackjack.domain.Score;
import com.blackjack.domain.card.Card;
import com.blackjack.domain.card.Denomination;
import com.blackjack.domain.card.Suit;

class HandsTest {
	@DisplayName("빈 리스트를 인자로 넣었을때 인스턴스 생성")
	@Test
	void constructor() {
		assertThat(new Hands(Collections.emptyList())).isInstanceOf(Hands.class);
	}

	@DisplayName("ACE를 포함하지 않았을 때 점수 생성")
	@Test
	void calculateScore_ExcludeAce_CreateTotalScore() {
		Hands hands = new Hands(
				Arrays.asList(Card.valueOf(Denomination.SIX, Suit.CLUB),
						Card.valueOf(Denomination.FIVE, Suit.DIAMOND)));
		assertThat(hands.calculateScore()).isEqualTo(Score.valueOf(11));
	}

	@DisplayName("ACE를 포함하고 상향하지 않았을 때 점수 생성")
	@Test
	void calculateScore_IncludeAceAndNotUpward_CreateTotalScore() {
		Hands hands = new Hands(Arrays.asList(Card.valueOf(Denomination.ACE, Suit.CLUB),
				Card.valueOf(Denomination.TEN, Suit.DIAMOND),
				Card.valueOf(Denomination.JACK, Suit.DIAMOND)));
		assertThat(hands.calculateScore()).isEqualTo(Score.valueOf(21));
	}

	@DisplayName("ACE를 포함하고 상향했을 때 점수 생성")
	@Test
	void calculateScore_IncludeAceAndUpward_CreateTotalScore() {
		Hands hands = new Hands(
				Arrays.asList(Card.valueOf(Denomination.ACE, Suit.CLUB), Card.valueOf(Denomination.TEN, Suit.DIAMOND)));
		assertThat(hands.calculateScore()).isEqualTo(Score.valueOf(21));
	}
}
