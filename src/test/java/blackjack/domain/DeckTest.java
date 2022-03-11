package blackjack.domain;

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
		Deck deck = new Deck();
		deck.distributeCard();
		assertThat(deck.getCards().size()).isEqualTo(51);
	}

	@Test
	void check_deck_empty_size() {
		Deck deck = new Deck();
		for (int i = 0; i < 52; i++) {
			deck.distributeCard();
		}
		assertThatThrownBy(() -> deck.distributeCard())
			.isInstanceOf(IllegalStateException.class)
			.hasMessageContaining("덱의 카드가 다 소진되었습니다.");
	}
}
