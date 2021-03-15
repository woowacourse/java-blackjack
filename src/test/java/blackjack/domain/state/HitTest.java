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
import blackjack.domain.participant.Money;

class HitTest {
	private PlayerState state;

	@BeforeEach
	void setUp() {
		Card firstCard = new Card(CardPattern.DIAMOND, CardNumber.TEN);
		Card secondCard = new Card(CardPattern.DIAMOND, CardNumber.THREE);
		state = StateFactory.drawTwoCards(firstCard, secondCard);
	}

	@Test
	@DisplayName("Hit 객체 확인")
	void checkHit() {
		assertThat(state).isInstanceOf(Hit.class);
	}

	@Test
	@DisplayName("끝난 상태인지 확인")
	void checkFinished() {
		assertFalse(state.isFinished());
	}

	@Test
	@DisplayName("false 들어왔을 때 Stay 객체 확인")
	void checkStay() {
		assertThat(state.keepContinue(false)).isInstanceOf(Stay.class);
	}

	@Test
	@DisplayName("true 들어왔을 때 Hit 객체 확인")
	void checkHitAgain() {
		assertThat(state.keepContinue(true)).isInstanceOf(Hit.class);
	}

	@Test
	@DisplayName("22넘으면 Bust 확인")
	void checkBust() {
		state = state.drawNewCard(new Card(CardPattern.CLOVER, CardNumber.NINE));
		assertThat(state).isInstanceOf(Bust.class);
	}

	@Test
	@DisplayName("22 안넘으면 Hit 확인")
	void checkStillHit() {
		state = state.drawNewCard(new Card(CardPattern.CLOVER, CardNumber.SEVEN));
		assertThat(state).isInstanceOf(Hit.class);
	}

	@Test
	@DisplayName("Bust 아님 확인")
	void isNotBust() {
		assertFalse(state.isBust());
	}

	@Test
	@DisplayName("Blackjack 아님 확인")
	void isNotBlackJack() {
		assertFalse(state.isBlackJack());
	}

	@Test
	@DisplayName("Running 시 옳지 않은 호출 예외 처리")
	void makeProfitException() {
		assertThatThrownBy(() -> state.makeProfit(new BlackJack(new ParticipantCards()), Money.of(100)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ERROR_MESSAGE_CALL);
	}
}