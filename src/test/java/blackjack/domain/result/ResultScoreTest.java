package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.exceptions.InvalidResultScoreException;

class ResultScoreTest {
	@Test
	void ResultScore_ScoreAndScoreType_GetInstance() {
		assertThat(new ResultScore(Score.valueOf(10), ScoreType.BLACKJACK)).isInstanceOf(ResultScore.class);
	}

	@Test
	void validate_NullScoreOrNullScoreType_InvalidResultScoreExceptionThrown() {
		assertThatThrownBy(() -> new ResultScore(null, ScoreType.BLACKJACK))
			.isInstanceOf(InvalidResultScoreException.class)
			.hasMessage(InvalidResultScoreException.NULL);
	}

	@Test
	void of_UserHand_GenerateInstance() {
		Hand hand = new Hand();
		hand.add(Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.EIGHT, Type.DIAMOND)));

		assertThat(ResultScore.of(hand)).isInstanceOf(ResultScore.class);
	}

	@Test
	void isBlackjack_ScoreType_CompareResultOfScoreType() {
		Hand hand = new Hand();
		hand.add(Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.ACE, Type.DIAMOND)));

		assertThat(ResultScore.of(hand).isBlackjack()).isTrue();
	}

	@Test
	void isBust_ScoreType_CompareResultOfScoreType() {
		Hand hand = new Hand();
		hand.add(Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.TEN, Type.SPADE),
			Card.of(Symbol.TWO, Type.DIAMOND)));

		assertThat(ResultScore.of(hand).isBust()).isTrue();
	}

	@Test
	void isNormal_ScoreType_CompareResultOfScoreType() {
		Hand hand = new Hand();
		hand.add(Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.FOUR, Type.DIAMOND)));

		assertThat(ResultScore.of(hand).isNormal()).isTrue();
	}
}
