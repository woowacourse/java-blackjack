package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class ParticipantTest {

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"", " ", "\t", "\n"})
	@DisplayName("이름이 공백이거나 빈칸일 경우")
	void validateName(String name) {
		assertThatThrownBy(() -> new Participant(name, new ArrayList<Card>())).isInstanceOf(
			IllegalArgumentException.class)
			.hasMessage("[Error] 이름은 공백이거나 빈칸일 수 없습니다.");
	}

	@Test
	@DisplayName("")
	void showHand() {
		Card card1 = new Card(Rank.RANK_A, Suit.HEART);
		Card card2 = new Card(Rank.RANK_9, Suit.SPADE);
		Participant participant = new Participant("pobi", List.of(card1, card2));
		assertThat(participant.showHand()).isEqualTo("pobi카드: A하트, 9스페이드");
	}
}
