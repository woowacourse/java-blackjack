package domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ScoreTest {

	@Nested
	@DisplayName("score 더하기")
	class Plus {

		@DisplayName("score에 주어진 Score를 더한 값을 더한다.")
		@Test
		void plusScore() {
			// given
			final Score score = new Score(0);
			final Score plusValue = new Score(10);

			// when
			final Score actual = score.plus(plusValue);

			// then
			assertThat(actual).isEqualTo(plusValue);
		}
	}

	@Nested
	@DisplayName("score 뺄셈")
	class minus {

		@DisplayName("score에 주어진 Score를 뺀 값을 반환한다.")
		@Test
		void minusScore() {
			// given
			final Score score = new Score(100);
			final Score minusValue = new Score(10);

			// when
			final Score actual = score.minus(minusValue);

			// then
			assertThat(actual).isEqualTo(new Score(90));
		}
	}

	@Nested
	@DisplayName("score 비교 연산")
	class isThan {

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

		@DisplayName("x가 주어진 Score 값보다 작은가")
		@Test
		void isLessThanScore() {
			// given
			final Score score = new Score(100);
			final Score moreThanValue = new Score(101);

			// when
			final boolean actual = score.isLessThan(moreThanValue);

			// then
			assertThat(actual).isTrue();
		}
	}
}
