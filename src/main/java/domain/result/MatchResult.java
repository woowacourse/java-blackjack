package domain.result;

public record MatchResult(PlayersMatchResult playersMatchResult, DealerMatchResult dealerMatchResult) {
    public PlayersBettingProfit calculateBettingProfit() {
        return playersMatchResult.calculateBettingProfit();
    }
}
