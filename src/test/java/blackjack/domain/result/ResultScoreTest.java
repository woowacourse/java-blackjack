package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;

class ResultScoreTest {
	@Test
	void ResultScore_ScoreAndScoreType_GetInstance() {
		assertThat(new ResultScore(Score.valueOf(10), )).isInstanceOf();
	}

	@Test
	void of_UserHand_GenerateInstance() {
		Hand hand = new Hand();
		hand.add(Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.EIGHT, Type.DIAMOND)));

		assertThat(ResultScore.of(hand)).isInstanceOf(ResultScore.class);
	}
}
