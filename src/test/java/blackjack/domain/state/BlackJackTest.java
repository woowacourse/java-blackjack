package blackjack.domain.state;

import static blackjack.controller.BlackJackController.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;

class BlackJackTest {
	private PlayerState state;

	@BeforeEach
	void setUp() {
		Card firstCard = new Card(CardPattern.CLOVER, CardNumber.TEN);
		Card secondCard = new Card(CardPattern.HEART, CardNumber.ACE);
		state = StateFactory.drawTwoCards(firstCard, secondCard);
	}

	@Test
	void checkBlackJack() {
		assertThat(state).isInstanceOf(BlackJack.class);
	}

	@Test
	void checkFinished() {
		assertTrue(state.isFinished());
	}

	@Test
	void exception() {
		assertThatThrownBy(() -> state.drawNewCard(new Card(CardPattern.DIAMOND, CardNumber.NINE)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ERROR_MESSAGE_CALL);
	}
}