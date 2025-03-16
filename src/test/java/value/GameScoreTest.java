package value;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import game.GameScore;

public class GameScoreTest {

	@Nested
	@DisplayName("score 더하기")
	class Plus {

		@DisplayName("score에 주어진 Score를 더한 값을 더한다.")
		@Test
		void plusScore() {
			// given
			final GameScore gameScore = GameScore.from(0);
			final GameScore plusValue = GameScore.from(10);

			// when
			final GameScore actual = gameScore.plus(plusValue);

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
			final GameScore gameScore = GameScore.from(100);
			final GameScore minusValue = GameScore.from(10);

			// when
			final GameScore actual = gameScore.minus(minusValue);

			// then
			assertThat(actual).isEqualTo(GameScore.from(90));
		}
	}

	@Nested
	@DisplayName("score 비교 연산")
	class isThan {

		@DisplayName("x가 주어진 score보다 더 큰가")
		@Test
		void isGreaterThanScore() {
			// given
			final GameScore gameScore = GameScore.from(100);
			final GameScore moreThanValue = GameScore.from(99);

			// when
			final boolean actual = gameScore.isGreaterThan(moreThanValue);

			// then
			assertThat(actual).isTrue();
		}

		@DisplayName("x가 주어진 Score 값보다 작은가")
		@Test
		void isLessThanScore() {
			// given
			final GameScore gameScore = GameScore.from(100);
			final GameScore moreThanValue = GameScore.from(101);

			// when
			final boolean actual = gameScore.isLessThan(moreThanValue);

			// then
			assertThat(actual).isTrue();
		}
	}
}
