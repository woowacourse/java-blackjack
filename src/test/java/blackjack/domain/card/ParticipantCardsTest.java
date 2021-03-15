package blackjack.domain.card;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantCardsTest {
	private ParticipantCards participantCards;

	@BeforeEach
	void setUp() {
		participantCards = new ParticipantCards();
		participantCards.addCard(new Card(CardPattern.SPADE, CardNumber.JACK));
	}

	@Test
	@DisplayName("새로운 카드 추가 확인")
	void addNewCard() {
		participantCards.addCard(new Card(CardPattern.DIAMOND, CardNumber.SEVEN));
		assertEquals(2, participantCards.getCardStream().count());
	}

	@Test
	@DisplayName("전체 포인트 계산 테스트")
	void addAllPoint() {
		assertEquals(10, participantCards.calculateJudgingPoint());
	}

	@Test
	@DisplayName("에이스 존재할 때 전체 포인트 계산 테스트")
	void addAllPointWithAce() {
		participantCards.addCard(new Card(CardPattern.SPADE, CardNumber.FIVE));
		participantCards.addCard(new Card(CardPattern.CLOVER, CardNumber.ACE));
		assertEquals(16, participantCards.calculateIncludeAce());
	}

	@Test
	@DisplayName("에이스기 1이어야 할 때 전체 포인트 계산 테스트")
	void addAllPointWithAceEleven() {
		participantCards.addCard(new Card(CardPattern.HEART, CardNumber.QUEEN));
		participantCards.addCard(new Card(CardPattern.CLOVER, CardNumber.ACE));
		assertEquals(21, participantCards.calculateIncludeAce());
	}

	@Test
	@DisplayName("에이스기 11이어야 할 때 전체 포인트 계산 테스트")
	void addAllPointWithAceOne() {
		participantCards.addCard(new Card(CardPattern.CLOVER, CardNumber.ACE));
		assertEquals(21, participantCards.calculateIncludeAce());
	}
}
