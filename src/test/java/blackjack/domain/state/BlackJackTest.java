package blackjack.domain.state;

import static blackjack.controller.BlackJackController.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;

class BlackJackTest {
	private PlayerState state;

	@BeforeEach
	void setUp() {
		Card firstCard = new Card(CardPattern.CLOVER, CardNumber.TEN);
		Card secondCard = new Card(CardPattern.HEART, CardNumber.ACE);
		state = StateFactory.drawTwoCards(firstCard, secondCard);
	}

	@Test
	@DisplayName("블랙잭 객체 확인")
	void checkBlackJack() {
		assertThat(state).isInstanceOf(BlackJack.class);
	}

	@Test
	@DisplayName("끝난 상태인지 확인")
	void checkFinished() {
		assertTrue(state.isFinished());
	}

	@Test
	@DisplayName("Bust 아님 확인")
	void isNotBust() {
		assertFalse(state.isBust());
	}

	@Test
	@DisplayName("블랙잭 맞음 확인")
	void isBlackJack() {
		assertTrue(state.isBlackJack());
	}

	@Test
	@DisplayName("이긴 경우 수익 계산")
	void calculateWinnerProfit() {
		Dealer dealer = new Dealer();
		dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.SEVEN));
		assertEquals(150, state.makeProfit(dealer, Money.of(100)));
	}

	@Test
	@DisplayName("끝난 상태인데 새 카드 받으려 하면 에러 호출")
	void exception() {
		assertThatThrownBy(() -> state.drawNewCard(new Card(CardPattern.DIAMOND, CardNumber.NINE)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ERROR_MESSAGE_CALL);
	}
}