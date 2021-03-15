package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
	private final List<Card> cards = new ArrayList<>();
	private Deck deck;

	@BeforeEach
	void setUp() {
		cards.add(new Card(CardPattern.HEART, CardNumber.SEVEN));
		cards.add(new Card(CardPattern.DIAMOND, CardNumber.FIVE));
		cards.add(new Card(CardPattern.SPADE, CardNumber.FOUR));
		cards.add(new Card(CardPattern.CLOVER, CardNumber.TWO));

		CardsGenerator cardsGenerator = () -> cards;
		deck = new Deck(cardsGenerator.makeCards());
	}

	@Test
	@DisplayName("카드 줄 때 첫번째 카드 주는지 확인")
	void checkFirstCard() {
		assertEquals(new Card(CardPattern.HEART, CardNumber.SEVEN), deck.dealCard());
	}

	@Test
	@DisplayName("카드 주고 나면 두번째 카드가 앞으로 오는지 확인")
	void checkSecondCard() {
		deck.dealCard();
		assertEquals(new Card(CardPattern.DIAMOND, CardNumber.FIVE), deck.dealCard());
	}

	@Test
	@DisplayName("덱 카드 섞이는지 확인")
	void deckShuffle() {
		Collections.shuffle(cards);
		assertNotEquals(new Card(CardPattern.HEART, CardNumber.SEVEN), deck.dealCard());
	}

	@Test
	@DisplayName("덱이 소진되었을 때 예외처리")
	void checkEmptyDeck() {
		assertThatThrownBy(() -> {
			for (int i = 0; i < 5; i++) {
				deck.dealCard();
			}
		}).isInstanceOf(IllegalArgumentException.class);
	}
}
