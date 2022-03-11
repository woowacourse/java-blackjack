package blackjack.domain.card;

import static blackjack.domain.exceptionMessages.CardExceptionMessage.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DeckTest {
	@Test
	void distribute_Card() {
		Deck deck = new Deck();
		assertThat(deck.distributeCard()).isInstanceOf(Card.class);
	}

	@Test
	void check_deck_size() {
		//given
		Deck deck = new Deck();
		//when
		deck.distributeCard();
		//then
		assertThat(deck.getCards().size()).isEqualTo(51);
	}

	@Test
	void check_deck_empty_size() {
		//given
		Deck deck = new Deck();
		//when
		for (int i = 0; i < 52; i++) {
			deck.distributeCard();
		}
		//then
		assertThatThrownBy(() -> deck.distributeCard())
			.isInstanceOf(IllegalStateException.class)
			.hasMessageContaining(EMPTY_DECK_EXCEPTION.getMessage());
	}
}
