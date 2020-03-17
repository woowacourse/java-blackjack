package com.blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.blackjack.exception.EmptyCardDeckException;

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
				Card.valueOf(Denomination.JACK, Suit.CLUB),
				Card.valueOf(Denomination.JACK, Suit.CLUB));

		assertThatThrownBy(() -> CardDeck.create(duplicatedCards))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("카드가 덱에 존재할 때 한 장을 뽑음")
	@Test
	void pop_DeckIsNotEmpty_ReturnCard() {
		CardDeck cardDeck = CardDeck.create();
		cardDeck.pop();
	}

	@DisplayName("카드가 덱에 존재하지 않을 때 예외 발생")
	@Test
	void pop_DeckIsEmpty_ExceptionThrown() {
		CardDeck cardDeck = CardDeck.create(Collections.emptyList());
		assertThatThrownBy(cardDeck::pop).isInstanceOf(EmptyCardDeckException.class);
	}
}
