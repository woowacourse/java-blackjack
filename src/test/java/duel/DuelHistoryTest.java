package duel;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import card.Count;

public class DuelHistoryTest {

	@Nested
	@DisplayName("승패를 기록한다.")
	class Write {

		@DisplayName("주어진 값이 DuelResult.WIN이라면 승리에 대한 기록을, DuelResult.LOSE라면 패배에 대한 기록을 한다.")
		@ParameterizedTest
		@CsvSource(value = {"WIN:1:0", "LOSE:0:1"}, delimiter = ':')
		void write(final DuelResult duelResult, final int winCount, final int loseCount) {
			// given
			final var duelHistory = new DuelHistory();
			final Count expectedWinCount = Count.from(winCount);
			final Count expectedLoseCount = Count.from(loseCount);

			// when
			duelHistory.write(duelResult);

			// then
			assertThat(duelHistory.getWinCount()).isEqualTo(expectedWinCount);
			assertThat(duelHistory.getLoseCount()).isEqualTo(expectedLoseCount);
		}
	}

	@Nested
	@DisplayName("검증 연산")
	class is {

		@DisplayName("우승이 더 많다면 true, 아니라면 false를 반환한다.")
		@Test
		void isWin() {
			// given
			final var win = new DuelHistory();
			final var lose = new DuelHistory();
			win.write(DuelResult.WIN);
			lose.write(DuelResult.LOSE);

			// when
			final boolean actualWin = win.isWin();
			final boolean actualLose = lose.isWin();

			// then
			assertThat(actualWin).isTrue();
			assertThat(actualLose).isFalse();
		}
	}
}
