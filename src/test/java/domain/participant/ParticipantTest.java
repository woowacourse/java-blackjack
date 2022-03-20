package domain.participant;

import static domain.card.Cards.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.participant.info.Betting;
import domain.participant.info.Hand;
import domain.participant.info.Name;

public class ParticipantTest {

	@ParameterizedTest(name = "손패가 버스트 되었는지 확인하는 기능 - case : {0}")
	@EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"ACE"})
	void isBurst(Denomination denomination) {
		Card card = new Card(denomination, Suit.HEART);
		Card card1 = new Card(Denomination.KING, Suit.HEART);
		Participant participant = new Participant(new Name("pobi"), new Hand(List.of(card, card1, card1)),
			new Betting(0));
		assertThat(participant.isBust()).isTrue();
	}

	@Test()
	@DisplayName("베스트 스코어 계산하는 기능")
	void getBestScore() {
		Card card1 = new Card(Denomination.KING, Suit.HEART);
		Card card2 = new Card(Denomination.ACE, Suit.SPADE);
		Card card3 = new Card(Denomination.ACE, Suit.CLOVER);
		Card card4 = new Card(Denomination.EIGHT, Suit.CLOVER);
		Participant participant = new Participant(new Name("pobi"),
			new Hand(List.of(card1, card2, card3, card4)), new Betting(0));
		assertThat(participant.getScore()).isEqualTo(20);
	}

	@Test
	@DisplayName("블랙잭 확인 테스트")
	void isBlackJack() {
		Hand hand = new Hand(List.of(ACE_CLOVER, QUEEN_CLOVER));
		Participant participant = new Participant(new Name("pobi"), hand, new Betting(0));
		assertThat(participant.isBlackJack()).isTrue();
	}

	@Test
	@DisplayName("버스트 확인 테스트")
	void isBust() {
		Hand hand = new Hand(List.of(TWO_CLOVER, QUEEN_CLOVER, QUEEN_CLOVER));
		Participant participant = new Participant(new Name("pobi"), hand, new Betting(0));
		assertThat(participant.isBust()).isTrue();
	}

	@Test
	@DisplayName("Ace가 있을 경우 계산_ACE가 11로 계산될 경우")
	void getScoreAceEleven() {
		Hand hand = new Hand(List.of(ACE_CLOVER, TWO_CLOVER));
		Participant participant = new Participant(new Name("pobi"), hand, new Betting(0));
		assertThat(participant.getScore()).isEqualTo(13);
	}

	@Test
	@DisplayName("Ace가 있을 경우 계산_ACE가 1로 계산될 경우")
	void getScoreAceOne() {
		Hand hand = new Hand(List.of(ACE_CLOVER, TWO_CLOVER, QUEEN_CLOVER));
		Participant participant = new Participant(new Name("pobi"), hand, new Betting(0));
		assertThat(participant.getScore()).isEqualTo(13);
	}
}
