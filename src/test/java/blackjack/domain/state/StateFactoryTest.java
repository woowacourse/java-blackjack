package blackjack.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;

public class StateFactoryTest {
	private Cards cards;

	@BeforeEach
	void setUp() {
		cards = new Cards();
	}

	@Test
	void checkBlackJack() {
		Card firstCard = new Card(CardPattern.DIAMOND, CardNumber.TEN);
		Card secondCard = new Card(CardPattern.DIAMOND, CardNumber.ACE);
		cards.addCard(firstCard);
		cards.addCard(secondCard);
		PlayerState state = StateFactory.drawTwoCards(firstCard, secondCard);
		assertThat(state).isInstanceOf(BlackJack.class);
	}

	@Test
	void checkHit() {
		Cards cards = new Cards();
		Card firstCard = new Card(CardPattern.DIAMOND, CardNumber.TEN);
		Card secondCard = new Card(CardPattern.DIAMOND, CardNumber.THREE);
		cards.addCard(firstCard);
		cards.addCard(secondCard);
		PlayerState state = StateFactory.drawTwoCards(firstCard, secondCard);
		assertThat(state).isInstanceOf(Hit.class);
	}
}
