package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import constant.DuelResult;

public class DuelHistoryTest {

	@Nested
	@DisplayName("승패를 기록한다.")
	class Write {

		@DisplayName("승패를 올바르게 기록한다.")
		@ParameterizedTest
		@CsvSource(value = {"WIN:1:0", "LOSE:0:1"}, delimiter = ':')
		void write(final DuelResult duelResult, final int expectedWinCount, final int expectedLoseCount) throws
			Exception {
			// given
			final var duelHistory = new DuelHistory();

			// when
			duelHistory.write(duelResult);

			// then
			assertThat(duelHistory.getWinCount()).isEqualTo(expectedWinCount);
			assertThat(duelHistory.getLoseCount()).isEqualTo(expectedLoseCount);
		}
	}
}
