package com.blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.blackjack.domain.Score;
import com.blackjack.domain.card.Card;
import com.blackjack.domain.card.CardNumber;
import com.blackjack.domain.card.CardSymbol;

class HandTest {
	@DisplayName("빈 리스트를 인자로 넣었을때 인스턴스 생성")
	@Test
	void constructor() {
		assertThat(new Hand(Collections.emptyList())).isInstanceOf(Hand.class);
	}

	@DisplayName("ACE를 포함하지 않았을 때 점수 생성")
	@Test
	void calculateScore_ExcludeAce_CreateTotalScore() {
		Hand hand = new Hand(
				Arrays.asList(Card.valueOf(CardNumber.SIX, CardSymbol.CLUB),
						Card.valueOf(CardNumber.FIVE, CardSymbol.DIAMOND)));
		assertThat(hand.calculate()).isEqualTo(new Score(11));
	}

	@DisplayName("ACE를 포함하고 상향하지 않았을 때 점수 생성")
	@Test
	void calculateScore_IncludeAceAndNotUpward_CreateTotalScore() {
		Hand hand = new Hand(Arrays.asList(Card.valueOf(CardNumber.ACE, CardSymbol.CLUB),
				Card.valueOf(CardNumber.TEN, CardSymbol.DIAMOND),
				Card.valueOf(CardNumber.JACK, CardSymbol.DIAMOND)));
		assertThat(hand.calculate()).isEqualTo(new Score(21));
	}

	@DisplayName("ACE를 포함하고 상향했을 때 점수 생성")
	@Test
	void calculateScore_IncludeAceAndUpward_CreateTotalScore() {
		Hand hand = new Hand(
				Arrays.asList(Card.valueOf(CardNumber.ACE, CardSymbol.CLUB),
						Card.valueOf(CardNumber.TEN, CardSymbol.DIAMOND)));
		assertThat(hand.calculate()).isEqualTo(new Score(21));
	}
}
