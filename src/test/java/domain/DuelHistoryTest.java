package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DuelHistoryTest {

	@Nested
	@DisplayName("승패를 기록한다.")
	class Write {

		@DisplayName("주어진 값이 DuelResult.WIN이라면 승리에 대한 기록을, DuelResult.LOSE라면 패배에 대한 기록을 한다.")
		@ParameterizedTest
		@CsvSource(value = {"WIN:1:0", "LOSE:0:1"}, delimiter = ':')
		void write(final DuelResult duelResult, final int expectedWinCount, final int expectedLoseCount) {
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
