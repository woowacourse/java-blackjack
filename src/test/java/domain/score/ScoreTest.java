package domain.score;

import static domain.card.Symbol.*;
import static domain.card.Type.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import domain.card.Card;

class ScoreTest {
	@DisplayName("음의 정수로 점수 객체 생성시 Illegal Exception 발생")
	@ParameterizedTest
	@ValueSource(ints = {-1, -5, -10})
	void construct_minus_score_exception_test(int score) {
		assertThatThrownBy(() -> Score.ofValue(score)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("점수 값의 크기 비교 후 앞의 점수가 더 큰 경우 참 반환")
	@ParameterizedTest
	@CsvSource(value = {"10,9,true", "10,10,false", "10,11,false"})
	void isBiggerThan(int myScoreValue, int otherScoreValue, boolean expected) {
		Score myScore = Score.ofValue(myScoreValue);
		Score otherScore = Score.ofValue(otherScoreValue);
		boolean actual = myScore.isBiggerThan(otherScore);
		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("점수 값의 크기 비교 후 두 점수가 같은 경우 참 반환")
	@ParameterizedTest
	@CsvSource(value = {"10,9,false", "10,10,true", "10,11,false"})
	void isSameWith(int myScoreValue, int otherScoreValue, boolean expected) {
		Score myScore = Score.ofValue(myScoreValue);
		Score otherScore = Score.ofValue(otherScoreValue);
		boolean actual = myScore.isSameWith(otherScore);
		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("점수 값의 크기 비교 후 뒤의 점수가 더 큰 경우 참 반환")
	@ParameterizedTest
	@CsvSource(value = {"10,9,false", "10,10,false", "10,11,true"})
	void isSmallerThan(int myScoreValue, int otherScoreValue, boolean expected) {
		Score myScore = Score.ofValue(myScoreValue);
		Score otherScore = Score.ofValue(otherScoreValue);
		boolean actual = myScore.isSmallerThan(otherScore);
		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("점수 합계가 올바르게 계산되는지 테스트")
	@Test
	void calculateTotalTest() {
		List<Card> cards = Arrays.asList(Card.of(CLOVER, TEN), Card.of(CLOVER, ACE), Card.of(HEART, ACE));
		Score actual = Score.calculateTotal(cards);
		assertThat(actual).isEqualTo(Score.ofValue(12));
	}

	@DisplayName("점수 객체의 값이 21점인 경우 Maximum 체크시 true 반환")
	@ParameterizedTest
	@CsvSource(value = {"21,true", "20,false", "22,false"})
	void check_maximum_score_test(int scoreValue, boolean expected) {
		Score score = Score.ofValue(scoreValue);
		boolean actual = score.isMaximum();
		assertThat(actual).isEqualTo(expected);
	}
}