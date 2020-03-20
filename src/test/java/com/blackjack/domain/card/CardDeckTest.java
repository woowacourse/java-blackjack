package com.blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {
	@DisplayName("카드 덱 생성자 테스트")
	@Test
	void constructor() {
		assertThat(CardDeck.create()).isInstanceOf(CardDeck.class);
	}

	@DisplayName("카드 덱 생성자 테스트")
	@Test
	void constructor_GivenDuplicatedCards_ExceptionThrown() {
		List<Card> duplicatedCards = Arrays.asList(
				Card.valueOf(CardNumber.JACK, CardSymbol.CLUB),
				Card.valueOf(CardNumber.JACK, CardSymbol.CLUB));

		assertThatThrownBy(() -> CardDeck.create(duplicatedCards))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("카드가 덱에 존재할 때 한 장을 뽑음")
	@Test
	void pop_DeckIsNotEmpty_ReturnCard() {
		CardDeck cardDeck = CardDeck.create();
		cardDeck.pop();
	}
}
