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
}
