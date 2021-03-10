package blackjack.domain.state;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;

class BlackJackTest {
	private PlayerState state;

	@BeforeEach
	void setUp() {
		Cards cards = new Cards();
		cards.addCard(new Card(CardPattern.CLOVER, CardNumber.TEN));
		cards.addCard(new Card(CardPattern.HEART, CardNumber.ACE));
		state = StateFactory.drawTwoCards(cards);
	}

	@Test
	void checkFinished() {
		assertTrue(state.isFinished());
	}

	@Test
	void exception() {
		assertThatThrownBy(() -> state.keepContinue(true)).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("옳지 않은 곳에서 호출");
	}
}