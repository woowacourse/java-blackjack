package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ShuffledDeckFactoryTest {
	DeckFactory deckFactory;

	@BeforeEach
	void setUp() {
		deckFactory = new ShuffledDeckFactory();
	}

	@Test
	void ShuffledDeckFactory() {
		assertThat(deckFactory).isNotNull();
	}

	@Test
	void create() {
		// when
		Drawable deck = Deck.ofDeckFactory(deckFactory);

		// then
		Set<Card> cards = new HashSet<>();
		for (int i = 0; i < 52; i++) {
			cards.add(deck.draw());
		}

		assertThat(cards.size()).isEqualTo(52);
	}
}