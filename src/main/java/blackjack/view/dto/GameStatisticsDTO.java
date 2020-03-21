package blackjack.view.dto;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.player.domain.report.GameReport;
import blackjack.player.domain.report.GameReports;

public class GameStatisticsDTO {
	private final String dealerResult;
	private final String gamblerResult;

	public GameStatisticsDTO(GameReports gameReports) {
		this.dealerResult = collectRecord(gameReports);
		this.gamblerResult = collectGamblerResult(gameReports.getReports());
	}

	private String collectGamblerResult(List<GameReport> gameReports) {
		return gameReports.stream()
			.map(gameReport ->
				String.format("%s: %d", getName(gameReport), gameReport.calculateGamblerProfit().getAmount()))
			.collect(Collectors.joining(System.lineSeparator()));
	}

	private String getName(GameReport gameReport) {
		return gameReport
			.getGambler()
			.getName();
	}

	private String collectRecord(GameReports gameReports) {
		return String.format("딜러 : %d", gameReports.calculateDealerProfit().getAmount());
	}

	public String getDealerResult() {
		return dealerResult;
	}

	public String getGamblerResult() {
		return gamblerResult;
	}
}
