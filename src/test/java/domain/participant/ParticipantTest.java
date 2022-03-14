package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;

public class ParticipantTest {

	Card card1;
	Card card2;
	Card card3;
	Card card4;

	@BeforeEach
	void setUp() {
		card1 = new Card(Rank.RANK_KNIGHT, Suit.HEART);
		card2 = new Card(Rank.RANK_ACE, Suit.SPADE);
		card3 = new Card(Rank.RANK_ACE, Suit.CLOVER);
		card4 = new Card(Rank.RANK_EIGHT, Suit.CLOVER);
	}

	@ParameterizedTest(name = "손패가 버스트 되었는지 확인하는 기능 - case : {0}")
	@EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"RANK_ACE"})
	void isBurst(Rank rank) {
		Card card = new Card(rank, Suit.HEART);
		Participant participant = new Participant(new Name("pobi"), new ArrayList<>(List.of(card, card1, card1)));
		assertThat(participant.isBust()).isTrue();
	}

	@ParameterizedTest(name = "손패가 MAX SCORE인지 확인하는 기능 - case : {0}, {1}, {2}")
	@CsvSource(value = {"RANK_ACE, RANK_ACE, RANK_NINE", "RANK_KNIGHT, RANK_QUEEN, RANK_ACE",
		"RANK_KNIGHT, RANK_EIGHT, RANK_THREE"})
	void isBlackJack(String input1, String input2, String input3) {
		Card card1 = new Card(Rank.valueOf(input1), Suit.HEART);
		Card card2 = new Card(Rank.valueOf(input2), Suit.SPADE);
		Card card3 = new Card(Rank.valueOf(input3), Suit.CLOVER);
		Participant participant = new Participant(new Name("pobi"), new ArrayList<>(List.of(card1, card2, card3)));
		assertThat(participant.isMaxScore()).isTrue();
	}

	@Test()
	@DisplayName("베스트 스코어 계산하는 기능")
	void getBestScore() {
		Participant participant = new Participant(new Name("pobi"),
			new ArrayList<>(List.of(card1, card2, card3, card4)));
		assertThat(participant.getBestScore()).isEqualTo(20);
	}
}
