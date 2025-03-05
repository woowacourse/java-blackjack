package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DuelHistoryTest {

	@Nested
	@DisplayName("승패를 기록한다.")
	class WriteDuelHistory {

		@DisplayName("올바르게 승패를 기록한다.")
		@ParameterizedTest
		@CsvSource(value = {"true:1:0", "false:0:1"}, delimiter = ':')
		public void writeDuelHistory(final boolean duelResult, final boolean winCount, final boolean loseCount) throws
			Exception {
			// given
			final var duelHistory = new DuelHistory();

			// when
			duelHistory.write(duelResult);

			// then
			assertThat(duelHistory.getWinCount()).isEqualTo(winCount);
			assertThat(duelHistory.getLoseCount()).isEqualTo(loseCount);
		}

	}
}
