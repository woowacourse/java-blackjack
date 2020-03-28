package domain.participant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.ParticipantCards;
import domain.card.Card;
import domain.card.Shape;
import domain.card.Symbol;

class ParticipantTest {
	Participant participant = new Participant(Name.create("참여자"));

	@Test
	@DisplayName("Participant 인스턴스가 카드를 받으면 제대로 카드 목록에 더해지는지")
	void addCardTest() {
		ParticipantCards cards = participant.getCards();
		participant.receive(new Card(Symbol.ACE, Shape.CLOVER));
		assertThat(cards.getSize()).isEqualTo(1);
		participant.receive(new Card(Symbol.ACE, Shape.DIAMOND));
		assertThat(cards.getSize()).isEqualTo(2);
	}

	@Test
	@DisplayName("Participant 인스턴스가 Hit할 수 있는지의 여부가 제대로 나오는지")
	void calculateScore() {
		participant.receive(new Card(Symbol.KING, Shape.CLOVER));
		participant.receive(new Card(Symbol.NINE, Shape.DIAMOND));
		assertThat(participant.canHit()).isEqualTo(true);
		participant.receive(new Card(Symbol.NINE, Shape.HEART));
		assertThat(participant.canHit()).isEqualTo(false);
	}

	@Test
	@DisplayName("버스트 확인이 제대로 되는지")
	void isBust() {
		participant.receive(new Card(Symbol.TEN, Shape.CLOVER));
		participant.receive(new Card(Symbol.NINE, Shape.CLOVER));
		assertThat(participant.isBust()).isEqualTo(false);
		participant.receive(new Card(Symbol.EIGHT, Shape.CLOVER));
		assertThat(participant.isBust()).isEqualTo(true);
	}

	@Test
	@DisplayName("블랙잭 확인이 제대로 되는지")
	void isBlackJack() {
		participant.receive(new Card(Symbol.KING, Shape.SPADE));
		assertThat(participant.isBlackJack()).isEqualTo(false);
		participant.receive(new Card(Symbol.ACE, Shape.DIAMOND));
		assertThat(participant.isBlackJack()).isEqualTo(true);
	}

	@Test
	@DisplayName("점수 계산이 제대로 되는지")
	void calculateScoreTest() {
		participant.receive(new Card(Symbol.KING, Shape.SPADE));
		participant.receive(new Card(Symbol.NINE, Shape.CLOVER));
		assertThat(participant.calculateScore()).isEqualTo(19);
		participant.receive(new Card(Symbol.ACE, Shape.DIAMOND));
		assertThat(participant.calculateScore()).isEqualTo(20);
	}
}
