package blackjack.view.dto;

import blackjack.player.domain.report.GameReport;
import blackjack.player.domain.report.GameReports;

import java.util.List;
import java.util.stream.Collectors;

public class GameStatisticsDTO {
	private final String dealerResult;
	private final String gamblerResult;

	public GameStatisticsDTO(GameReports gameReports) {
		this.dealerResult = collectRecord(gameReports);
		this.gamblerResult = collectGamblerResult(gameReports.getReports());
	}

	private String collectGamblerResult(List<GameReport> gameReports) {
		return gameReports.stream()
				.map(gameReport -> String.format("%s: %s", gameReport.getName(), gameReport.getMessage()))
				.collect(Collectors.joining(System.lineSeparator()));
	}

	private String collectRecord(GameReports gameReports) {
		int gamblerWinCount = gameReports.getGamblerWinCount();
		int gamblerLoseCount = gameReports.getGamblerLoseCount();
		int draw = gameReports.getGamblerDrawCount();

		return String.format("딜러: %d승 %d패 %d무", gamblerLoseCount, gamblerWinCount, draw);
	}

	public String getDealerResult() {
		return dealerResult;
	}

	public String getGamblerResult() {
		return gamblerResult;
	}
}
