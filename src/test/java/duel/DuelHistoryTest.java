package duel;

import static org.assertj.core.api.SoftAssertions.*;

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
			assertSoftly(s -> {
				s.assertThat(duelHistory.getWinCount()).isEqualTo(expectedWinCount);
				s.assertThat(duelHistory.getDrawCount()).isEqualTo(expectedDrawCount);
				s.assertThat(duelHistory.getLoseCount()).isEqualTo(expectedLoseCount);
			});
		}
	}

	@Nested
	@DisplayName("검증 연산")
	class is {

		@DisplayName("우승이 가장 많다면 true, 아니라면 false를 반환하라")
		@Test
		void isWin() {
			// given
			final var win = new DuelHistory();
			final var lose = new DuelHistory();
			final var draw = new DuelHistory();
			win.write(DuelResult.WIN);
			draw.write(DuelResult.WIN);
			draw.write(DuelResult.DRAW);
			draw.write(DuelResult.DRAW);
			lose.write(DuelResult.LOSE);

			// when
			final boolean actualWin = win.isWin();
			final boolean actualDraw = draw.isWin();
			final boolean actualLose = lose.isWin();

			// then
			assertSoftly(s -> {
				s.assertThat(actualWin).isTrue();
				s.assertThat(actualDraw).isFalse();
				s.assertThat(actualLose).isFalse();
			});
		}

		@DisplayName("무승부가 가장 많다면 true, 아니라면 false를 반환하라")
		@Test
		void isDraw() {
			// given
			final var win = new DuelHistory();
			final var lose = new DuelHistory();
			final var draw = new DuelHistory();
			win.write(DuelResult.WIN);
			draw.write(DuelResult.WIN);
			draw.write(DuelResult.DRAW);
			draw.write(DuelResult.DRAW);
			lose.write(DuelResult.LOSE);

			// when
			final boolean actualWin = win.isDraw();
			final boolean actualDraw = draw.isDraw();
			final boolean actualLose = lose.isDraw();

			// then
			assertSoftly(s -> {
				s.assertThat(actualWin).isFalse();
				s.assertThat(actualDraw).isTrue();
				s.assertThat(actualLose).isFalse();
			});
		}
	}

	@Nested
	@DisplayName("결과 계산")
	class CalculateDuelResult {

		@DisplayName("가장 많은 값에 따라 DuelResult를 반환한다.")
		@Test
		void isWin() {
			// given
			final var win = new DuelHistory();
			final var lose = new DuelHistory();
			final var draw = new DuelHistory();
			win.write(DuelResult.WIN);
			draw.write(DuelResult.WIN);
			draw.write(DuelResult.DRAW);
			draw.write(DuelResult.DRAW);
			lose.write(DuelResult.LOSE);

			// when
			final DuelResult actualWin = win.calculateDuelResult();
			final DuelResult actualDraw = draw.calculateDuelResult();
			final DuelResult actualLose = lose.calculateDuelResult();

			// then
			assertSoftly(s -> {
				s.assertThat(actualWin).isEqualTo(DuelResult.WIN);
				s.assertThat(actualDraw).isEqualTo(DuelResult.DRAW);
				s.assertThat(actualLose).isEqualTo(DuelResult.LOSE);
			});
		}
	}
}
