package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RankTest {

	@Nested
	@DisplayName("주어진 점수가 Bust되어 있고, ACE가 존재한다면 ACE를 최소점수로 만들어 계산한다.")
	class IfOverThanBustScoreAceIsMIN {

		@DisplayName("점수를 올바르게 ACE 최소값으로 계산한다.")
		@ParameterizedTest
		@CsvSource(value = {"20:2:20", "22:0:22", "22:1:12", "44:4:14"}, delimiter = ':')
		void ifOverThanBustScoreAceIsMIN(final int score, final int aceCount, final int expected) {
			// given & when
			final var actual = Rank.ifOverThanBustScoreAceIsMIN(score, aceCount);

			// then
			assertThat(actual).isEqualTo(expected);
		}

	}

}
