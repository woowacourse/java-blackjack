package blackjack.player.domain.report;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.card.domain.GameResult;

class GameReportsTest {

	private static GameReports gameReports = new GameReports(
		Arrays.asList(
			new GameReport("allen", GameResult.WIN),
			new GameReport("allen", GameResult.WIN),
			new GameReport("allen", GameResult.WIN),
			new GameReport("allen", GameResult.DRAW),
			new GameReport("allen", GameResult.DRAW),
			new GameReport("allen", GameResult.LOSE
			)
		)
	);

	@DisplayName("GameReports의 승자 카운팅 확인")
	@Test
	void getGamblerWinCount() {
		assertThat(gameReports.getGamblerWinCount()).isEqualTo(3);
	}

	@DisplayName("GameReports의 무승부 카운팅 확인")
	@Test
	void getGamblerDrawCount() {
		assertThat(gameReports.getGamblerDrawCount()).isEqualTo(2);
	}

	@DisplayName("GameReports의 패자 카운팅 확인")
	@Test
	void getGamblerLoseCount() {
		assertThat(gameReports.getGamblerLoseCount()).isEqualTo(1);
	}
}