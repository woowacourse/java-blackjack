package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Hand;
import domain.card.Suit;

public class ParticipantTest {

	@ParameterizedTest(name = "손패가 버스트 되었는지 확인하는 기능 - case : {0}")
	@EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"ACE"})
	void isBurst(Denomination denomination) {
		Card card = new Card(denomination, Suit.HEART);
		Card card1 = new Card(Denomination.KNIGHT, Suit.HEART);
		Participant participant = new Participant(new Name("pobi"), new Hand(List.of(card, card1, card1)),
			new Betting(0));
		assertThat(participant.isBust()).isTrue();
	}

	@ParameterizedTest(name = "손패가 MAX SCORE인지 확인하는 기능 - case : {0}, {1}, {2}")
	@CsvSource(value = {"ACE, ACE, NINE", "KNIGHT, QUEEN, ACE", "KNIGHT, EIGHT, THREE"})
	void isBlackJack(String input1, String input2, String input3) {
		Card card1 = new Card(Denomination.valueOf(input1), Suit.HEART);
		Card card2 = new Card(Denomination.valueOf(input2), Suit.SPADE);
		Card card3 = new Card(Denomination.valueOf(input3), Suit.CLOVER);
		Participant participant = new Participant(new Name("pobi"), new Hand(List.of(card1, card2, card3)),
			new Betting(0));
		assertThat(participant.isMaxScore()).isTrue();
	}

	@Test()
	@DisplayName("베스트 스코어 계산하는 기능")
	void getBestScore() {
		Card card1 = new Card(Denomination.KNIGHT, Suit.HEART);
		Card card2 = new Card(Denomination.ACE, Suit.SPADE);
		Card card3 = new Card(Denomination.ACE, Suit.CLOVER);
		Card card4 = new Card(Denomination.EIGHT, Suit.CLOVER);
		Participant participant = new Participant(new Name("pobi"),
			new Hand(List.of(card1, card2, card3, card4)), new Betting(0));
		assertThat(participant.getBestScore()).isEqualTo(20);
	}
}
