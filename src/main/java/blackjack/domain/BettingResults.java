package blackjack.domain;

import blackjack.domain.result.WinningResult;
import java.util.List;

public class BettingResults {
    private final List<BettingResult> bettingResults;

    public BettingResults(final List<BettingResult> bettingResults) {
        this.bettingResults = bettingResults;
    }

    public static BettingResults of(final List<PlayerBetting> playerBettings, final WinningResult winningResult) {
        List<BettingResult> bettingResults = winningResult.getParticipantsResult().entrySet().stream()
                .flatMap(entry -> playerBettings.stream()
                        .filter(playerBetting -> playerBetting.isName(entry.getKey()))
                        .map(playerBetting -> BettingResult.of(entry.getValue(), playerBetting)))
                .toList();
        return new BettingResults(bettingResults);
    }

    public List<BettingResult> getBettingResults() {
        return bettingResults;
    }
}
