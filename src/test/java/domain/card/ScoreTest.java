package domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ScoreTest {

	@Nested
	@DisplayName("score 더하기")
	class Plus {

		@DisplayName("score 더하기")
		@Test
		void plus() {
			// given
			final Score score = new Score(0);
			final int plusValue = 10;

			// when
			final Score actual = score.plus(plusValue);

			// then
			assertThat(actual.getValue()).isEqualTo(plusValue);
		}
	}

	@Nested
	@DisplayName("score 뺄셈")
	class minus {

		@DisplayName("score 빼기")
		@Test
		void minus() {
			// given
			final Score score = new Score(100);
			final int minusValue = 10;

			// when
			final Score actual = score.minus(minusValue);

			// then
			assertThat(actual.getValue()).isEqualTo(90);
		}
	}

	@Nested
	@DisplayName("score 비교 연산")
	class isThan {

		@DisplayName("x가 주어진 값보다 더 큰가")
		@Test
		void isGreaterThan() {
			// given
			final Score score = new Score(100);
			final int moreThanValue = 99;

			// when
			final boolean actual = score.isGreaterThan(moreThanValue);

			// then
			assertThat(actual).isTrue();
		}

		@DisplayName("x가 주어진 score보다 더 큰가")
		@Test
		void isGreaterThanScore() {
			// given
			final Score score = new Score(100);
			final Score moreThanValue = new Score(99);

			// when
			final boolean actual = score.isGreaterThan(moreThanValue);

			// then
			assertThat(actual).isTrue();
		}

		@DisplayName("x가 주어진 값보다 작은가")
		@Test
		void isLessThan() {
			// given
			final Score score = new Score(100);
			final int moreThanValue = 101;

			// when
			final boolean actual = score.isGreaterThan(moreThanValue);

			// then
			assertThat(actual).isFalse();
		}

		@DisplayName("x가 주어진 Score 값보다 작은가")
		@Test
		void isLessThanScore() {
			// given
			final Score score = new Score(100);
			final Score moreThanValue = new Score(101);

			// when
			final boolean actual = score.isLessThan(moreThanValue);

			// then
			assertThat(actual).isFalse();
		}
	}
}
