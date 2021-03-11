package blackjack.domain.state;

import static blackjack.controller.BlackJackController.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;

class BustTest {
	private Bust state;

	@BeforeEach
	void setUp() {
		Cards cards = new Cards();
		state = new Bust(cards);
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