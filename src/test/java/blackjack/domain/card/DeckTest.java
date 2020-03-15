package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DeckTest {
	private static Stream<Arguments> provideEmptyDeck() {
		int initDeckSize = CardFactory.create().size();
		Deck deck = new Deck(CardFactory.create());

		for (int i = 0; i < initDeckSize; i++) {
			deck.draw();
		}
		return Stream.of(Arguments.of(deck));
	}

	@Test
	void Deck_GetCardsFromCardFactory_GenerateInstance() {
		assertThat(new Deck(CardFactory.create())).isInstanceOf(Deck.class);
	}

	@Test
	void validate_DuplicateExistCards_InvalidDeckExceptionThrown() {
		LinkedList<Card> cards = new LinkedList<>(Arrays.asList(
			new Card(Symbol.TEN, Type.CLUB),
			new Card(Symbol.TEN, Type.CLUB)));

		assertThatThrownBy(() -> new Deck(cards))
			.isInstanceOf(InvalidDeckException.class)
			.hasMessage(InvalidDeckException.INVALID);
	}

	@Test
	void draw_UserDrawCardFromDeck_ReturnCard() {
		Deck deck = new Deck(CardFactory.create());

		assertThat(deck.draw()).isInstanceOf(Card.class);
	}

	@ParameterizedTest
	@MethodSource("provideEmptyDeck")
	void draw_DrawFromEmptyDeck_InvalidDeckExceptionThrown(Deck deck) {
		assertThatThrownBy(deck::draw)
			.isInstanceOf(InvalidDeckException.class)
			.hasMessage(InvalidDeckException.DECK_EMPTY);
	}
}
