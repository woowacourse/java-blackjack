package blackjack.domain;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.machine.Card;
import blackjack.domain.machine.Score;

public class ScoreTest {

	@DisplayName("패에 A가 없을 경우 계산 테스트")
	@Test
	void notAInHand() {
		List<Card> hand = List.of(
			Card.THREE_DIAMOND,
			Card.FOUR_DIAMOND);

		int score = Score.from(hand).getSum();

		assertThat(score).isEqualTo(7);
	}

	@DisplayName("패에 A가 하나 있을 경우 계산 테스트")
	@Test
	void OneAInHand() {
		List<Card> hand = List.of(
			Card.THREE_DIAMOND,
			Card.FOUR_DIAMOND,
			Card.A_HEART);

		int score = Score.from(hand).getSum();

		assertThat(score).isEqualTo(18);
	}

	@DisplayName("패에 A가 한개 초과 있을 경우 계산 테스트")
	@Test
	void overOneAInHand() {
		List<Card> hand = List.of(
			Card.THREE_HEART,
			Card.FOUR_HEART,
			Card.A_HEART,
			Card.A_SPADE);

		int score = Score.from(hand).getSum();

		assertThat(score).isEqualTo(19);
	}
}
