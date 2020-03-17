package blackjack.domain.card;

import blackjack.domain.card.exceptions.ScoreException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {
	static Score zero;
	static Score five;
	static Score six;
	static Score eleven;

	@BeforeAll
	static void beforeAll() {
		zero = Score.zero();
		five = Score.of(5);
		six = Score.of(6);
		eleven = Score.of(11);
	}

	@ParameterizedTest
	@MethodSource("of_ZeroOrPlusNum_IsNotNull")
	void of_ZeroOrPlusNum_IsNotNull(Score score) {
		assertThat(score).isNotNull();
	}

	static Stream<Score> of_ZeroOrPlusNum_IsNotNull() {
		return Stream.of(zero, five, six, eleven);
	}

	@Test
	void of_MinusNum_ThrowScoreException() {
		assertThatThrownBy(() -> Score.of(-1))
				.isInstanceOf(ScoreException.class);
	}

	@ParameterizedTest
	@MethodSource("add_TwoScore_ReturnAddedScore")
	void add_TwoScore_ReturnAddedScore(Score a, Score b, Score expect) {
		assertThat(a.add(b)).isEqualTo(expect);
	}

	static Stream<Arguments> add_TwoScore_ReturnAddedScore() {
		return Stream.of(Arguments.of(zero, five, five),
				Arguments.of(zero, six, six),
				Arguments.of(zero, zero, zero),
				Arguments.of(five, six, eleven));
	}

	@ParameterizedTest
	@MethodSource("isUnder_ScoreSmallerThanNum_ReturnTrue")
	void isUnder_ScoreSmallerThanNum_ReturnTrue(Score score, int num) {
		assertThat(score.isUnder(num)).isTrue();
	}

	static Stream<Arguments> isUnder_ScoreSmallerThanNum_ReturnTrue() {
		return Stream.of(Arguments.of(zero, 1),
				Arguments.of(five, 6),
				Arguments.of(five, 21),
				Arguments.of(six, 7),
				Arguments.of(eleven, 12));
	}

	@ParameterizedTest
	@MethodSource("isUnder_ScoreSameOrBiggerThanNum_ReturnFalse")
	void isUnder_ScoreSameOrBiggerThanNum_ReturnFalse(Score score, int num) {
		assertThat(score.isUnder(num)).isFalse();
	}

	static Stream<Arguments> isUnder_ScoreSameOrBiggerThanNum_ReturnFalse() {
		return Stream.of(Arguments.of(zero, 0),
				Arguments.of(five, 5),
				Arguments.of(five, 4),
				Arguments.of(five, 0),
				Arguments.of(six, 6),
				Arguments.of(eleven, 11));
	}

	@ParameterizedTest
	@MethodSource("isOver_ScoreBiggerThanNum_ReturnTrue")
	void isOver_ScoreBiggerThanNum_ReturnTrue(Score score, int num) {
		assertThat(score.isOver(num)).isTrue();
	}

	static Stream<Arguments> isOver_ScoreBiggerThanNum_ReturnTrue() {
		return Stream.of(Arguments.of(five, 4),
				Arguments.of(six, 5),
				Arguments.of(eleven, 10),
				Arguments.of(eleven, 0),
				Arguments.of(eleven, 5));
	}

	@ParameterizedTest
	@MethodSource("isOver_ScoreSameOrSmallerThanNum_ReturnFalse")
	void isOver_ScoreSameOrSmallerThanNum_ReturnFalse(Score score, int num) {
		assertThat(score.isOver(num)).isFalse();
	}

	static Stream<Arguments> isOver_ScoreSameOrSmallerThanNum_ReturnFalse() {
		return Stream.of(Arguments.of(zero, 0),
				Arguments.of(five, 5),
				Arguments.of(six, 6),
				Arguments.of(eleven, 11),
				Arguments.of(eleven, 12),
				Arguments.of(eleven, 18));
	}

	@ParameterizedTest
	@MethodSource("getScore_Score_IsEqualToExpect")
	void getScore_Score_IsEqualToExpect(Score score, int expect) {
		assertThat(score.getScore()).isEqualTo(expect);
	}

	static Stream<Arguments> getScore_Score_IsEqualToExpect() {
		return Stream.of(Arguments.of(zero, 0),
				Arguments.of(five, 5),
				Arguments.of(six, 6),
				Arguments.of(eleven, 11));
	}
}