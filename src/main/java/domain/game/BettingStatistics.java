package domain.game;

import domain.participants.Player;
import domain.participants.PlayerName;
import java.util.Map;
import java.util.stream.Collectors;

public class BettingStatistics {
    private final Map<PlayerName, BettingResultAmount> bettingResult;

    public BettingStatistics(Map<Player, GameResult> bettingResult) {
        this.bettingResult = bettingResult.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getPlayerName(),
                        entry -> new BettingResultAmount(entry.getKey().getBettingAmount(), entry.getValue())
                ));
    }

    public BettingResultAmount calculateDealerBettingResult() {
        int dealerResult = bettingResult.values().stream()
                .mapToInt(bettingResult -> -bettingResult.getMoney())
                .sum();
        return new BettingResultAmount(dealerResult);
    }

    public Map<PlayerName, BettingResultAmount> getBettingResult() {
        return bettingResult;
    }
}
