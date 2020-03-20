package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.exceptions.InvalidScoreException;

class ScoreTest {
	@Test
	void validate_Negative_InvalidScoreExceptionThrown() {
		assertThatThrownBy(() -> Score.valueOf(-1))
			.isInstanceOf(InvalidScoreException.class)
			.hasMessage(InvalidScoreException.INVALID);
	}

	@Test
	void valueOf_InputInteger_ReturnInstance() {
		assertThat(Score.valueOf(10)).isInstanceOf(Score.class)
			.extracting("score").isEqualTo(10);
	}

	@ParameterizedTest
	@EnumSource(value = Symbol.class)
	void valueOf_InputCard_ReturnInstance(Symbol symbol) {
		Card card = Card.of(symbol, Type.CLUB);

		assertThat(Score.valueOf(card)).isInstanceOf(Score.class)
			.extracting("score").isEqualTo(card.getScore());
	}

	@ParameterizedTest
	@NullSource
	void valueOf_InputNull_NullPointerExceptionThrown(Card card) {
		assertThatThrownBy(() -> Score.valueOf(card))
			.isInstanceOf(InvalidScoreException.class)
			.hasMessage(InvalidScoreException.CARD_NULL);
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 5, 10})
	void add_InputScore_addScore(int value) {
		Score score = Score.valueOf(value);

		assertThat(Score.ZERO.add(score)).extracting("score").isEqualTo(value);
	}

	@ParameterizedTest
	@NullSource
	void add_InputScore_addScore(Score score) {
		assertThatThrownBy(() -> Score.valueOf(10).add(score))
			.isInstanceOf(InvalidScoreException.class)
			.hasMessage(InvalidScoreException.SCORE_NULL);
	}

	@ParameterizedTest
	@CsvSource(value = {"8,false", "9,true"})
	void isLowerThan_InputIntegerScore_ReturnCompareResult(int value, boolean expected) {
		Score score = Score.valueOf(9);

		assertThat(score.isLowerThan(value)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"9,true", "10,false"})
	void isMoreThan_InputIntegerScore_ReturnCompareResult(int value, boolean expected) {
		Score score = Score.valueOf(9);

		assertThat(score.isMoreThan(value)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"9,true", "10,false"})
	void isEqual_InputIntegerScore_ReturnCompareResult(int value, boolean expected) {
		Score score = Score.valueOf(9);

		assertThat(score.isEqual(value)).isEqualTo(expected);
	}

	@Test
	void compareTo_CompareScore_ReturnCompareByIntScore() {
		Score score1 = Score.valueOf(17);
		Score score2 = Score.valueOf(16);

		assertThat(score1.compareTo(score2)).isEqualTo(Integer.compare(17, 16));
	}
}
