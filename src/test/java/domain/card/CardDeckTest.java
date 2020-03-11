package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CardDeckTest {
	@DisplayName("카드 뽑기 테스트 - count 전달")
	@Test
	void cardDraw() {
		CardDeck cardDeck = new CardDeck();
		int count = 2;

		assertThat(cardDeck.draw(2).size()).isEqualTo(count);
	}

	@DisplayName("카드 뽑기 테스트 - count 미전달")
	@Test
	void cardDraw2() {
		CardDeck cardDeck = new CardDeck();

		assertThat(cardDeck.draw().size()).isEqualTo(1);
	}
}
