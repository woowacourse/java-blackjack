package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class ParticipantTest {

	@ParameterizedTest(name = "이름이 공백이거나 빈칸일 경우")
	@NullAndEmptySource
	@ValueSource(strings = {"", " ", "\t", "\n"})
	void validateName(String name) {
		assertThatThrownBy(() -> new Participant(name, new ArrayList<>())).isInstanceOf(
			IllegalArgumentException.class)
			.hasMessage("[Error] 이름은 공백이거나 빈칸일 수 없습니다.");
	}

	@Test
	@DisplayName("자신의 손패를 문자열로 반환하는 기능")
	void showHand() {
		Card card1 = new Card(Rank.RANK_A, Suit.HEART);
		Card card2 = new Card(Rank.RANK_9, Suit.SPADE);
		Participant participant = new Participant("pobi", new ArrayList<>(List.of(card1, card2)));
		assertThat(participant.showHand()).isEqualTo("pobi카드: A하트, 9스페이드");
	}

	@Test
	@DisplayName("손패에 새로운 카드를 추가하는 기능")
	void addCard() {
		Card card1 = new Card(Rank.RANK_A, Suit.HEART);
		Card card2 = new Card(Rank.RANK_9, Suit.SPADE);
		Card card3 = new Card(Rank.RANK_8, Suit.CLOVER);
		Participant participant = new Participant("pobi", new ArrayList<>(List.of(card1, card2)));
		participant.addCard(card3);
		assertThat(participant.showHand()).isEqualTo("pobi카드: A하트, 9스페이드, 8클로버");
	}

	@ParameterizedTest(name = "손패가 버스트 되었는지 확인하는 기능 - case : {0}")
	@EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"RANK_A"})
	void isBurst(Rank rank) {
		Card card1 = new Card(rank, Suit.HEART);
		Card card2 = new Card(Rank.RANK_J, Suit.SPADE);
		Card card3 = new Card(Rank.RANK_K, Suit.CLOVER);
		Participant participant = new Participant("pobi", new ArrayList<>(List.of(card1, card2, card3)));
		assertThat(participant.isBurst()).isTrue();
	}
}
