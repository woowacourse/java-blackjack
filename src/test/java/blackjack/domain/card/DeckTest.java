package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

	@Test
	@DisplayName("카드 덱은 52장의 카드로 이루어져 있다.")
	void deckSizeTest() {
		Deck deck = new Deck();

		assertThat(deck.getCards().size()).isEqualTo(52);
	}

	@Test
	@DisplayName("맨 위의 카드를 한 장 뽑는다.")
	void drawTest() {
		Deck deck = new Deck();

		assertThat(deck.draw()).isEqualTo(new Card(CardShape.HEART, CardNumber.ACE));
	}
}
