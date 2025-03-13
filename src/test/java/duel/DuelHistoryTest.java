package duel;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import value.Count;

public class DuelHistoryTest {

	@Nested
	@DisplayName("승패를 기록한다.")
	class Write {

		@DisplayName("주어진 값이 DuelResult에 따라, 값을 기록한다.")
		@ParameterizedTest
		@CsvSource(value = {"WIN:1:0:0", "DRAW:0:1:0", "LOSE:0:0:1"}, delimiter = ':')
		void write(final DuelResult duelResult, final int winCount, final int drawCount, final int loseCount) {
			// given
			final var duelHistory = new DuelHistory();
			final Count expectedWinCount = Count.from(winCount);
			final Count expectedDrawCount = Count.from(drawCount);
			final Count expectedLoseCount = Count.from(loseCount);

			// when
			duelHistory.write(duelResult);

			// then
			assertThat(duelHistory.getWinCount()).isEqualTo(expectedWinCount);
			assertThat(duelHistory.getDrawCount()).isEqualTo(expectedDrawCount);
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
