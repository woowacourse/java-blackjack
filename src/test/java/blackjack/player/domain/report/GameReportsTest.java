package blackjack.player.domain.report;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.card.domain.GameResult;
import blackjack.player.domain.component.Money;
import blackjack.player.domain.component.PlayerInfo;

class GameReportsTest {
	private static Stream<Arguments> gameReportsProvider() {
		return Stream.of(
			Arguments.of(
				new GameReports(Arrays.asList(
					new GameReport(new PlayerInfo("allen", Money.create(1000)), GameResult.WIN),
					new GameReport(new PlayerInfo("pobi", Money.create(1000)), GameResult.WIN),
					new GameReport(new PlayerInfo("bebop", Money.create(1000)), GameResult.WIN)
				)), -3000
			),
			Arguments.of(
				new GameReports(Arrays.asList(
					new GameReport(new PlayerInfo("allen", Money.create(1000)), GameResult.DRAW),
					new GameReport(new PlayerInfo("pobi", Money.create(1000)), GameResult.DRAW),
					new GameReport(new PlayerInfo("bebop", Money.create(1000)), GameResult.DRAW)
				)), 0
			),
			Arguments.of(
				new GameReports(Arrays.asList(
					new GameReport(new PlayerInfo("allen", Money.create(1000)), GameResult.LOSE),
					new GameReport(new PlayerInfo("pobi", Money.create(1000)), GameResult.LOSE),
					new GameReport(new PlayerInfo("bebop", Money.create(1000)), GameResult.LOSE)
				)), 3000
			),
			Arguments.of(
				new GameReports(Arrays.asList(
					new GameReport(new PlayerInfo("allen", Money.create(1000)), GameResult.BLACKJACK_WIN),
					new GameReport(new PlayerInfo("pobi", Money.create(1000)), GameResult.BLACKJACK_WIN),
					new GameReport(new PlayerInfo("bebop", Money.create(1000)), GameResult.BLACKJACK_WIN)
				)), -4500
			)
		);
	}

	@DisplayName("딜러의 총 수익 계산")
	@ParameterizedTest
	@MethodSource("gameReportsProvider")
	void name(GameReports gameReports, long expect) {
		Money dealerProfit = gameReports.calculateDealerProfit();
		assertThat(dealerProfit.getAmount()).isEqualTo(expect);
	}
}