package blackjack.view.dto;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.card.GameReport;
import blackjack.card.GameResult;

public class GameStatisticsDTO {
	private final String dealerResult;
	private final String gamblerResult;

	public GameStatisticsDTO(List<GameReport> gameReports) {
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
			.filter(gameReport -> gameReport.getGameResult() == GameResult.WIN)
			.count();
		int gamblerLoseCount = (int)gameReports.stream()
			.filter(gameReport -> gameReport.getGameResult() == GameResult.LOSE)
			.count();
		int draw = (int)gameReports.stream()
			.filter(gameReport -> gameReport.getGameResult() == GameResult.DRAW)
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
