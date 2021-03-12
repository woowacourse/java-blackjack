package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
	private Deck deck;

	@BeforeEach
	void setUp() {
		deck = new Deck();
	}

	@Test
	@DisplayName("카드 주고 나면 두번째 카드가 앞으로 오는지 확인")
	void checkIndex() {
		Card secondCard = new Card(CardPattern.HEART, CardNumber.TWO);
		deck.dealCard();
		assertEquals(secondCard, deck.dealCard());
	}

	@Test
	@DisplayName("덱 카드 섞이는지 확인")
	void deckShuffle() {
		Card first = new Card(CardPattern.HEART, CardNumber.ACE);
		deck.shuffleCards();
		assertNotEquals(first, deck.dealCard());
	}

	@Test
	@DisplayName("덱이 소진되었을 때 예외처리")
	void checkEmptyDeck() {
		assertThatThrownBy(() -> {
			for (int i = 0; i < 53; i++) {
				deck.dealCard();
			}
		}).isInstanceOf(IllegalArgumentException.class);
	}
}
