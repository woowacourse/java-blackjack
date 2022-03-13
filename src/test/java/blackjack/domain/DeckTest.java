package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DeckTest {
	@Test
	void distribute_Card() {
		Deck.generateDeck();
		assertThat(Deck.distributeCard()).isInstanceOf(Card.class);
	}

	@Test
	void check_Deck_size() {
		Deck.generateDeck();
		Deck.distributeCard();
		assertThat(Deck.getCardsSize()).isEqualTo(51);
	}

	@Test
	void check_Deck_empty_size() {
		Deck.generateDeck();
		for (int i = 0; i < 52; i++) {
			Deck.distributeCard();
		}
		assertThatThrownBy(Deck::distributeCard)
			.isInstanceOf(IllegalStateException.class)
			.hasMessageContaining("덱의 카드가 다 소진되었습니다.");
	}
}
