package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;

public class CardDeckTest {
	@Test
	void create() {
		assertThat(new CardDeck()).isInstanceOf(CardDeck.class);
	}

	@Test
	void drawOne() {
		CardDeck cardDeck = new CardDeck();
		assertThat(cardDeck.pop()).isInstanceOf(Card.class);
	}
}