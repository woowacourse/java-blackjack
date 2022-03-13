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

	@Test
	@DisplayName("자신의 손패를 문자열로 반환하는 기능")
	void showHand() {
		Participant participant = new Participant(new Name("pobi"), new ArrayList<>(List.of(card1, card2)));
		assertThat(participant.showHand()).isEqualTo("pobi카드: K하트, A스페이드");
	}

	@Test
	@DisplayName("자신의 손패와 베스트 스코어를 문자열로 반환하는 기능")
	void showHandAndBestScore() {
		Participant participant = new Participant(new Name("pobi"), new ArrayList<>(List.of(card1, card2)));
		assertThat(participant.showHandAndBestScore()).isEqualTo("pobi카드: K하트, A스페이드 - 결과 : 21");
	}

	@Test
	@DisplayName("손패에 새로운 카드를 추가하는 기능")
	void addCard() {
		Participant participant = new Participant(new Name("pobi"), new ArrayList<>(List.of(card1, card2)));
		participant.addCard(card4);
		assertThat(participant.showHand()).isEqualTo("pobi카드: K하트, A스페이드, 8클로버");
	}

	@ParameterizedTest(name = "손패가 버스트 되었는지 확인하는 기능 - case : {0}")
	@EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"RANK_A"})
	void isBurst(Rank rank) {
		Card card = new Card(rank, Suit.HEART);
		Participant participant = new Participant(new Name("pobi"), new ArrayList<>(List.of(card, card1, card1)));
		assertThat(participant.isBust()).isTrue();
	}

	@ParameterizedTest(name = "손패가 MAX SCORE인지 확인하는 기능 - case : {0}, {1}, {2}")
	@CsvSource(value = {"RANK_A, RANK_A, RANK_9", "RANK_K, RANK_Q, RANK_A", "RANK_K, RANK_8, RANK_3"})
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
