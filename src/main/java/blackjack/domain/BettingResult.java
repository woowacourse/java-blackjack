package blackjack.domain;

public enum BettingResult {
    BLACKJACK(1.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0);

    private final double rateOfProfit;

    BettingResult(double rateOfProfit) {
        this.rateOfProfit = rateOfProfit;
    }

    public static BettingResult of(MatchResult matchResult, Player player) {
        if (matchResult == MatchResult.WIN && player.isBlackjack()) {
            return BLACKJACK;
        }
        if (matchResult == MatchResult.WIN) {
            return WIN;
        }
        if (matchResult == MatchResult.DRAW) {
            return DRAW;
        }
        return LOSE;
    }

    public double calculateProfit(BetAmount betAmount) {
        return betAmount.money() * rateOfProfit;
    }
}