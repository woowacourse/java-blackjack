package card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import value.Count;
import value.Score;

public class RankTest {

	@Nested
	@DisplayName("주어진 점수가 Bust되어 있고, ACE가 존재한다면 ACE를 최소점수로 만들어 계산한다.")
	class IfOverThanBustScoreAceIsMIN {

		@DisplayName("점수가 bustScore를 초과했다면, ACE의 점수를 최소값으로 계산한다.")
		@ParameterizedTest
		@CsvSource(value = {"20:2:20", "22:0:22", "22:1:12", "44:4:14"}, delimiter = ':')
		void ifOverThanBustScoreAceIsMIN(final int score, final int aceCount, final int expected) {
			// given
			final int bustScore = 21;

			// when
			final var actual = Rank.ifOverThanBustScoreAceIsMIN(Score.from(score), Count.from(aceCount),
				Score.from(bustScore));

			// then
			assertThat(actual.value()).isEqualTo(expected);
		}

	}

}
