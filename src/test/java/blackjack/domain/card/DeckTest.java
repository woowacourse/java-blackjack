package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DeckTest {
	@Test
	void Deck_GetCardsFromCardRepository_GenerateInstance() {
		assertThat(new Deck()).isInstanceOf(Deck.class);
	}

	@Test
	void draw_UserDrawCardFromDeck_ReturnCard() {
		Deck deck = new Deck();

		assertThat(deck.draw()).isInstanceOf(Card.class);
	}

	@ParameterizedTest
	@MethodSource("provideEmptyDeck")
	void refill_EmptyDeck_RefillDeck(Deck deck) {
		assertThat(deck.draw()).isInstanceOf(Card.class);
	}

	private static Stream<Arguments> provideEmptyDeck() {
		int initDeckSize = CardRepository.cards().size();
		Deck deck = new Deck();

		for (int i = 0; i < initDeckSize; i++) {
			deck.draw();
		}
		return Stream.of(Arguments.of(deck));
	}
}
