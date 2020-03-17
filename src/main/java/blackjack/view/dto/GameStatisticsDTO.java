package blackjack.view.dto;

import blackjack.domain.report.GameReport;
import blackjack.domain.report.GameReports;

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
                .map(gameReport -> String.format("%s: %f", gameReport.getName(), gameReport.getMoney()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String collectRecord(GameReports gameReports) {
        double dealerMoney = gameReports.getTotalGamblerMoney() * -1;
        return String.format("딜러: %f원", dealerMoney);
    }

    public String getDealerResult() {
        return dealerResult;
    }

    public String getGamblerResult() {
        return gamblerResult;
    }
}
