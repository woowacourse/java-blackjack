package domain.card;

import exception.EmptyDeckException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {
	@Test
	@DisplayName("덱에서 한장을 꺼낸 경우 사이즈가 1만큼 줄어드는지")
	void deckPopTest() {
		Deck deck = new Deck();
		deck.drawCard();
		assertThat(deck.getDeck()).hasSize(51);
	}

	@Test
	@DisplayName("덱의 모든 카드를 소진한 경우")
	void checkEmptyDeck() {
		Deck deck = new Deck();
		for (int i = 0; i < 52; i++) {
			deck.drawCard();
		}
		assertThatThrownBy(() -> {
			deck.drawCard();
		}).isInstanceOf(EmptyDeckException.class);
	}
}
