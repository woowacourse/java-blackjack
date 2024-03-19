package blackjack.model.player;

import blackjack.model.betting.BettingMoney;
import blackjack.model.dealer.Dealer;

public enum MatchResult {
    BLACKJACK_WIN(1.5),
    WIN(1),
    LOSE(-1),
    PUSH(0);

    private final double profitRate;

    MatchResult(final double profitRate) {
        this.profitRate = profitRate;
    }

    public static MatchResult determine(final Dealer dealer, final Player player) {
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return MatchResult.PUSH;
        }
        if (dealer.isBlackjack()) {
            return MatchResult.LOSE;
        }
        if (player.isBlackjack()) {
            return MatchResult.BLACKJACK_WIN;
        }
        return determineByScore(dealer, player);
    }

    private static MatchResult determineByScore(final Dealer dealer, final Player player) {
        int playerScore = player.calculateCardsScore();
        int dealerScore = dealer.calculateCardsScore();
        if (player.isBust()) {
            return MatchResult.LOSE;
        }
        if (dealer.isBust() || (playerScore > dealerScore)) {
            return MatchResult.WIN;
        }
        if (playerScore == dealerScore) {
            return MatchResult.PUSH;
        }
        return MatchResult.LOSE;
    }

    public int calculateProfit(BettingMoney bettingMoney) {
        return (int) (bettingMoney.amount() * profitRate);
    }
}
