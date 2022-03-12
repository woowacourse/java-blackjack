package blackjack.domain;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {

	@DisplayName("패에 A가 없을 경우 계산 테스트")
	@Test
	void notAInHand() {
		List<Card> hand = List.of(
			new Card("3하트",3),
			new Card("4하트", 4));

		int score = Score.from(hand).getSum();

		assertThat(score).isEqualTo(7);
	}

	@DisplayName("패에 A가 하나 있을 경우 계산 테스트")
	@Test
	void OneAInHand() {
		List<Card> hand = List.of(
			new Card("3하트",3),
			new Card("4하트", 4),
			new Card("A하트",1));

		int score = Score.from(hand).getSum();

		assertThat(score).isEqualTo(18);
	}

	@DisplayName("패에 A가 한개 초과 있을 경우 계산 테스트")
	@Test
	void overOneAInHand() {
		List<Card> hand = List.of(
			new Card("3하트",3),
			new Card("4하트", 4),
			new Card("A하트",1),
			new Card("A스페이드", 1));

		int score = Score.from(hand).getSum();

		assertThat(score).isEqualTo(19);
	}
}
