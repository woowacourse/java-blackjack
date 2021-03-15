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
import blackjack.domain.card.ParticipantCards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;

class StayTest {
	private PlayerState state;

	@BeforeEach
	void setUp() {
		ParticipantCards participantCards = new ParticipantCards();
		Card firstCard = new Card(CardPattern.DIAMOND, CardNumber.TEN);
		Card secondCard = new Card(CardPattern.DIAMOND, CardNumber.THREE);
		participantCards.addCard(firstCard);
		participantCards.addCard(secondCard);
		state = StateFactory.drawTwoCards(firstCard, secondCard);
	}

	@Test
	@DisplayName("stay인지 확인")
	void checkStay() {
		assertThat(state.keepContinue(false)).isInstanceOf(Stay.class);
	}

	@Test
	@DisplayName("끝난 상태인지 확인")
	void checkFinished() {
		state = state.keepContinue(false);
		assertTrue(state.isFinished());
	}

	@Test
	@DisplayName("이긴 경우 수익 계산")
	void calculateWinnerProfit() {
		Dealer dealer = new Dealer();
		dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.SEVEN));
		state = state.keepContinue(false);
		assertEquals(100, state.makeProfit(dealer.getPlayerState(), Money.of(100)));
	}

	@Test
	@DisplayName("비긴 경우 수익 계산")
	void calculateDrawProfit() {
		Dealer dealer = new Dealer();
		dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.SEVEN));
		dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.SIX));
		state = state.keepContinue(false);
		assertEquals(0, state.makeProfit(dealer.getPlayerState(), Money.of(100)));
	}

	@Test
	@DisplayName("진 경우 수익 계산")
	void calculateLoserProfit() {
		Dealer dealer = new Dealer();
		dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.SEVEN));
		dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.SEVEN));
		state = state.keepContinue(false);
		assertEquals(-100, state.makeProfit(dealer.getPlayerState(), Money.of(100)));
	}

	@Test
	@DisplayName("Bust 아님 확인")
	void isNotBust() {
		state = state.keepContinue(false);
		assertFalse(state.isBust());
	}

	@Test
	@DisplayName("Blackjack 아님 확인")
	void isNotBlackJack() {
		state = state.keepContinue(false);
		assertFalse(state.isBlackJack());
	}

	@Test
	@DisplayName("끝난 상태인데 새 카드 받으려 하면 에러 호출")
	void exception() {
		Card card = new Card(CardPattern.DIAMOND, CardNumber.NINE);
		state = state.keepContinue(false);
		assertThatThrownBy(() -> state.drawNewCard(card))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ERROR_MESSAGE_CALL);
	}
}