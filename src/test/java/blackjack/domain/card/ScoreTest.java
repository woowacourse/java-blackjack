package blackjack.domain.card;

import blackjack.domain.card.exceptions.ScoreException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
		zero = Score.of(0);
		five = Score.of(5);
		six = Score.of(6);
		eleven = Score.of(11);
	}

	@ParameterizedTest
	@MethodSource("of_IsNotNull")
	void of_IsNotNull(Score score) {
		assertThat(score).isNotNull();
	}

	static Stream<Score> of_IsNotNull() {
		return Stream.of(zero, five, six, eleven);
	}

	@Test
	void of_IfMinus_ThrowScoreException() {
		assertThatThrownBy(() -> Score.of(-1))
				.isInstanceOf(ScoreException.class);
	}

	@ParameterizedTest
	@MethodSource("add")
	void add(Score a, Score b, Score expect) {
		assertThat(a.add(b)).isEqualTo(expect);
	}

	static Stream<Arguments> add() {
		return Stream.of(Arguments.of(zero, five, five),
				Arguments.of(zero, six, six),
				Arguments.of(zero, zero, zero),
				Arguments.of(five, six, eleven));
	}

	@ParameterizedTest
	@MethodSource("isUnder_ReturnTrue")
	void isUnder_ReturnTrue(Score score, int num) {
		assertThat(score.isUnder(num)).isTrue();
	}

	static Stream<Arguments> isUnder_ReturnTrue() {
		return Stream.of(Arguments.of(zero, 1),
				Arguments.of(five, 6),
				Arguments.of(five, 21),
				Arguments.of(six, 7),
				Arguments.of(eleven, 12));
	}

	@ParameterizedTest
	@MethodSource("isUnder_ReturnFalse")
	void isUnder_ReturnFalse(Score score, int num) {
		assertThat(score.isUnder(num)).isFalse();
	}

	static Stream<Arguments> isUnder_ReturnFalse() {
		return Stream.of(Arguments.of(zero, 0),
				Arguments.of(five, 5),
				Arguments.of(five, 4),
				Arguments.of(five, 0),
				Arguments.of(six, 6),
				Arguments.of(eleven, 11));
	}

	@ParameterizedTest
	@MethodSource("isOver_ReturnTrue")
	void isOver_ReturnTrue(Score score, int num) {
		assertThat(score.isOver(num)).isTrue();
	}

	static Stream<Arguments> isOver_ReturnTrue() {
		return Stream.of(Arguments.of(five, 4),
				Arguments.of(six, 5),
				Arguments.of(eleven, 10),
				Arguments.of(eleven, 0),
				Arguments.of(eleven, 5));
	}

	@ParameterizedTest
	@MethodSource("isOver_ReturnFalse")
	void isOver_ReturnFalse(Score score, int num) {
		assertThat(score.isOver(num)).isFalse();
	}

	static Stream<Arguments> isOver_ReturnFalse() {
		return Stream.of(Arguments.of(zero, 0),
				Arguments.of(five, 5),
				Arguments.of(six, 6),
				Arguments.of(eleven, 11),
				Arguments.of(eleven, 12),
				Arguments.of(eleven, 18));
	}

	@ParameterizedTest
	@MethodSource("getScore")
	void getScore(Score score, int expect) {
		assertThat(zero.getScore()).isEqualTo(0);
		assertThat(five.getScore()).isEqualTo(5);
		assertThat(six.getScore()).isEqualTo(6);
		assertThat(eleven.getScore()).isEqualTo(11);
	}

	static Stream<Arguments> getScore() {
		return Stream.of(Arguments.of(zero, 0),
				Arguments.of(five, 5),
				Arguments.of(six, 6),
				Arguments.of(eleven, 11));
	}

}