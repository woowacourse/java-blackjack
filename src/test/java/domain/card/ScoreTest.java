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
}
