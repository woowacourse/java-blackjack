package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import blackjack.domain.card.Card;
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
			.hasMessage(InvalidResultScoreException.SCORE_OR_SCORE_TYPE_NULL);
	}

	@Test
	void of_UserHand_GenerateInstance() {
		List<Card> cards = Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.EIGHT, Type.DIAMOND));

		assertThat(ResultScore.of(cards)).isInstanceOf(ResultScore.class);
	}

	@ParameterizedTest
	@NullAndEmptySource
	void validate_Null_InvalidResultScoreExceptionThrown(List<Card> value) {
		assertThatThrownBy(() -> ResultScore.of(value))
			.isInstanceOf(InvalidResultScoreException.class)
			.hasMessage(InvalidResultScoreException.CARDS_EMPTY);
	}

	@Test
	void calculateScore_SumCards() {
		List<Card> cards = Arrays.asList(
			Card.of(Symbol.TWO, Type.CLUB),
			Card.of(Symbol.EIGHT, Type.DIAMOND));

		Score expected = Score.valueOf(10);
		assertThat(ResultScore.of(cards).getScore()).isEqualTo(expected);
	}

	@Test
	void calculateScore_WithAceCard_SumCards() {
		List<Card> cards = Arrays.asList(
			Card.of(Symbol.TWO, Type.CLUB),
			Card.of(Symbol.EIGHT, Type.DIAMOND));

		Score expected = Score.valueOf(10);
		assertThat(ResultScore.of(cards).getScore()).isEqualTo(expected);
	}

	@Test
	void aceHandled_InputScore_ReturnAceHandledScore() {
		List<Card> cards = Arrays.asList(
			Card.of(Symbol.ACE, Type.CLUB),
			Card.of(Symbol.EIGHT, Type.DIAMOND));

		Score expected = Score.valueOf(19);
		assertThat(ResultScore.of(cards).getScore()).isEqualTo(expected);
	}

	@Test
	void isBlackjack_ScoreType_CompareResultOfScoreType() {
		List<Card> cards = Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.ACE, Type.DIAMOND));

		assertThat(ResultScore.of(cards).isBlackjack()).isTrue();
	}

	@Test
	void isBust_ScoreType_CompareResultOfScoreType() {
		List<Card> cards = Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.TEN, Type.SPADE),
			Card.of(Symbol.TWO, Type.DIAMOND));

		assertThat(ResultScore.of(cards).isBust()).isTrue();
	}

	@Test
	void isNormal_ScoreType_CompareResultOfScoreType() {
		List<Card> cards = Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.FOUR, Type.DIAMOND));

		assertThat(ResultScore.of(cards).isNormal()).isTrue();
	}

	@Test
	void compareTo_ResultScore_CompareByScore() {
		ResultScore resultScore1 = new ResultScore(Score.valueOf(17), ScoreType.NORMAL);
		ResultScore resultScore2 = new ResultScore(Score.valueOf(19), ScoreType.NORMAL);

		assertThat(resultScore1.compareTo(resultScore2)).isEqualTo(Integer.compare(17, 19));
	}
}
