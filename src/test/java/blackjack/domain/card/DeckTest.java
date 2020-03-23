package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import blackjack.domain.exceptions.InvalidDeckException;

class DeckTest {
	@Test
	void Deck_GetCardsFromCardFactory_GenerateInstance() {
		assertThat(new Deck(CardFactory.create())).isInstanceOf(Deck.class);
	}

	@ParameterizedTest
	@NullSource
	void validateEmpty_Null_InvalidDeckExceptionThrown(List<Card> value) {
		assertThatThrownBy(() -> new Deck(value))
			.isInstanceOf(InvalidDeckException.class)
			.hasMessage(InvalidDeckException.EMPTY);
	}

	@Test
	void validateDuplication_DuplicateExistCards_InvalidDeckExceptionThrown() {
		List<Card> cards = Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.TEN, Type.CLUB));

		assertThatThrownBy(() -> new Deck(cards))
			.isInstanceOf(InvalidDeckException.class)
			.hasMessage(InvalidDeckException.DUPLICATE);
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

	private static Stream<Arguments> provideEmptyDeck() {
		int initDeckSize = CardFactory.create().size();
		Deck deck = new Deck(CardFactory.create());

		for (int i = 0; i < initDeckSize; i++) {
			deck.draw();
		}

		return Stream.of(Arguments.of(deck));
	}
}
