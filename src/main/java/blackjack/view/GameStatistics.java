package blackjack.view;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.GameReport;

public class GameStatistics {
	private final String dealerResult;
	private final String gamblerResult;

	public GameStatistics(List<GameReport> gameReports) {
		this.dealerResult = collectRecord(gameReports);
		this.gamblerResult = collectGamblerResult(gameReports);
	}

	private String collectGamblerResult(List<GameReport> gameReports) {
		return gameReports.stream()
			.map(gameReport -> String.format("%s: %s", gameReport.getName(), gameReport.getMessage()))
			.collect(Collectors.joining(System.lineSeparator()));
	}

	private String collectRecord(List<GameReport> gameReports) {
		int gamblerWinCount = (int)gameReports.stream()
			.filter(GameReport::isWin)
			.count();
		int gamblerLoseCount = (int)gameReports.stream()
			.filter(GameReport::isLose)
			.count();
		int draw = (int)gameReports.stream()
			.filter(GameReport::isDraw)
			.count();

		return String.format("딜러: %d승 %d패 %d무", gamblerLoseCount, gamblerWinCount, draw);
	}

	public String getDealerResult() {
		return dealerResult;
	}

	public String getGamblerResult() {
		return gamblerResult;
	}
}
