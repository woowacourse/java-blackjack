package blackjack.domain.state;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;

class HitTest {
	private Cards cards;
	private PlayerState state;

	@BeforeEach
	void setUp() {
		cards = new Cards();
		Card firstCard = new Card(CardPattern.DIAMOND, CardNumber.TEN);
		Card secondCard = new Card(CardPattern.DIAMOND, CardNumber.THREE);
		state = StateFactory.drawTwoCards(firstCard, secondCard);
	}

	@Test
	void checkHit() {
		assertThat(state).isInstanceOf(Hit.class);
	}

	@Test
	void checkFinished() {
		assertFalse(state.isFinished());
	}

	@Test
	void checkStay() {
		assertThat(state.keepContinue(false)).isInstanceOf(Stay.class);
	}

	@Test
	void checkHitAgain() {
		assertThat(state.keepContinue(true)).isInstanceOf(Hit.class);
	}

	@Test
	void checkBust() {
		Hit hit = new Hit(cards);
		hit.drawNewCard(new Card(CardPattern.CLOVER, CardNumber.NINE));
	}
}