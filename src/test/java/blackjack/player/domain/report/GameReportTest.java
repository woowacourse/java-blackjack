package blackjack.player.domain.report;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import blackjack.card.domain.GameResult;

class GameReportTest {

	@DisplayName("플레이어 이름이 없을때 게임결과를 만들면 Exception")
	@ParameterizedTest
	@NullAndEmptySource
	void test(String arongName) {
		assertThatThrownBy(() -> {
			new GameReport(arongName, GameResult.WIN);
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("승패 정보가 없을 때 게임결과를 만들면 Exception")
	@ParameterizedTest
	@NullSource
	void test2(GameResult gameResult) {
		assertThatThrownBy(() -> {
			new GameReport("allen", gameResult);
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void isWin() {
		GameReport gameReport = new GameReport("allen", GameResult.WIN);

		assertThat(gameReport.isWin()).isTrue();
	}

	@Test
	void isDraw() {
		GameReport gameReport = new GameReport("allen", GameResult.DRAW);

		assertThat(gameReport.isDraw()).isTrue();
	}

	@Test
	void isLose() {
		GameReport gameReport = new GameReport("allen", GameResult.LOSE);

		assertThat(gameReport.isLose()).isTrue();
	}
}