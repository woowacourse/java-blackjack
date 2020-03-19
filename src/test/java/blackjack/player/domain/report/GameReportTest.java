package blackjack.player.domain.report;

import static blackjack.player.domain.component.PlayerInfoHelper.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import blackjack.card.domain.GameResult;
import blackjack.player.domain.component.PlayerInfo;

class GameReportTest {

	@DisplayName("플레이어 정보가 없으면 Exception")
	@ParameterizedTest
	@NullSource
	void test(PlayerInfo arongPlayerInfo) {
		assertThatThrownBy(() -> {
			new GameReport(arongPlayerInfo, GameResult.WIN);
		}).isInstanceOf(NullPointerException.class);
	}

	@DisplayName("승패 정보가 없을 때 게임결과를 만들면 Exception")
	@ParameterizedTest
	@NullSource
	void test2(GameResult gameResult) {
		assertThatThrownBy(() -> {
			new GameReport(aPlayerInfo("allen"), gameResult);
		}).isInstanceOf(NullPointerException.class);
	}

	@Test
	void isWin() {
		GameReport gameReport = new GameReport(aPlayerInfo("allen"), GameResult.WIN);

		assertThat(gameReport.isWin()).isTrue();
	}

	@Test
	void isDraw() {
		GameReport gameReport = new GameReport(aPlayerInfo("allen"), GameResult.DRAW);

		assertThat(gameReport.isDraw()).isTrue();
	}

	@Test
	void isLose() {
		GameReport gameReport = new GameReport(aPlayerInfo("allen"), GameResult.LOSE);

		assertThat(gameReport.isLose()).isTrue();
	}
}