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

class BustTest {
	private PlayerState state;

	@BeforeEach
	void setUp() {
		ParticipantCards participantCards = new ParticipantCards();
		state = new Bust(participantCards);
	}

	@Test
	@DisplayName("끝난 상태인지 확인")
	void checkFinished() {
		assertTrue(state.isFinished());
	}

	@Test
	@DisplayName("Bust 맞음 확인")
	void isBust() {
		assertTrue(state.isBust());
	}

	@Test
	@DisplayName("Blackjack 아님 확인")
	void isNotBlackJack() {
		assertFalse(state.isBlackJack());
	}

	@Test
	@DisplayName("Bust 수익 계산")
	void calculateDrawProfit() {
		Dealer dealer = new Dealer();
		dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.SEVEN));
		dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.SIX));
		state = state.keepContinue(false);
		assertEquals(-100, state.makeProfit(dealer.getPlayerState(), Money.of(100)));
	}

	@Test
	@DisplayName("끝난 상태인데 새 카드 받으려 하면 에러 호출")
	void exception() {
		assertThatThrownBy(() -> state.drawNewCard(new Card(CardPattern.DIAMOND, CardNumber.NINE)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ERROR_MESSAGE_CALL);
	}
}