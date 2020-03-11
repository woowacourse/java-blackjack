package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
}
