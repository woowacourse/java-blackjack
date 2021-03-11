package blackjack.domain.state;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;

class StayTest {
	private PlayerState state;

	@BeforeEach
	void setUp() {
		Cards cards = new Cards();
		Card firstCard = new Card(CardPattern.DIAMOND, CardNumber.TEN);
		Card secondCard = new Card(CardPattern.DIAMOND, CardNumber.THREE);
		cards.addCard(firstCard);
		cards.addCard(secondCard);
		state = StateFactory.drawTwoCards(firstCard, secondCard);
	}

	@Test
	void checkFinished() {
		state = state.keepContinue(false);
		assertTrue(state.isFinished());
	}

	@Test
	void exception() {
		state = state.keepContinue(false);
		assertThatThrownBy(() -> state.drawNewCard(new Card(CardPattern.DIAMOND, CardNumber.NINE)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("옳지 않은 곳에서 호출");
	}
}