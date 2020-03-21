package blackjack.domain.card;

import blackjack.domain.card.exceptions.ScoreException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
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

	@DisplayName("of()가 0 이상인 수가 들어왔을 때 인스턴스를 생성하는지 테스트")
	@ParameterizedTest
	@MethodSource("of_ZeroOrPlusNum_IsNotNull")
	void of_ZeroOrPlusNum_IsNotNull(Score score) {
		assertThat(score).isNotNull();
	}

	static Stream<Score> of_ZeroOrPlusNum_IsNotNull() {
		return Stream.of(zero, five, six, eleven);
	}

	@DisplayName("of()가 마이너스 숫자가 들어왔을 때 예외를 던지는지 테스트")
	@Test
	void of_MinusNum_ThrowScoreException() {
		assertThatThrownBy(() -> Score.of(-1))
				.isInstanceOf(ScoreException.class);
	}

	@DisplayName("add()가 적절하게 더하는지 테스트")
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

	@DisplayName("isUnder()이 자신보다 작은 값이 들어왔을때 true를 반환하는지 테스트")
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

	@DisplayName("isUnder()이 자신보다 큰 값이 들어왔을때 false를 반환하는지 테스트")
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

	@DisplayName("isOver()가 자신보다 큰 값이 들어왔을때 true를 반환하는지 테스트")
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

	@DisplayName("isOver()가 자신보다 작은 값이 들어왔을때 true를 반환하는지 테스트")
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
}