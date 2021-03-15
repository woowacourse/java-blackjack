package blackjack.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.ParticipantCards;

public class StateFactoryTest {
	private ParticipantCards participantCards;

	@BeforeEach
	void setUp() {
		participantCards = new ParticipantCards();
	}

	@Test
	@DisplayName("BlackJack 생성 테스트")
	void checkBlackJack() {
		Card firstCard = new Card(CardPattern.DIAMOND, CardNumber.TEN);
		Card secondCard = new Card(CardPattern.DIAMOND, CardNumber.ACE);
		participantCards.addCard(firstCard);
		participantCards.addCard(secondCard);
		PlayerState state = StateFactory.drawTwoCards(firstCard, secondCard);
		assertThat(state).isInstanceOf(BlackJack.class);
	}

	@Test
	@DisplayName("Hit 생성 테스트")
	void checkHit() {
		ParticipantCards participantCards = new ParticipantCards();
		Card firstCard = new Card(CardPattern.DIAMOND, CardNumber.TEN);
		Card secondCard = new Card(CardPattern.DIAMOND, CardNumber.THREE);
		participantCards.addCard(firstCard);
		participantCards.addCard(secondCard);
		PlayerState state = StateFactory.drawTwoCards(firstCard, secondCard);
		assertThat(state).isInstanceOf(Hit.class);
	}
}
