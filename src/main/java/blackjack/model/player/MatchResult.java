package blackjack.model.player;

import blackjack.model.betting.BettingMoney;
import blackjack.model.dealer.Dealer;

public enum MatchResult {
    BLACKJACK_WIN(2.5),
    WIN(2),
    LOSE(0),
    PUSH(1);

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
        return determineByTotalScore(dealer, player);
    }

    private static MatchResult determineByTotalScore(final Dealer dealer, final Player player) {
        int playerTotal = player.calculateCardsTotalScore();
        int dealerTotal = dealer.calculateCardsTotalScore();
        if (player.isBust()) {
            return MatchResult.LOSE;
        }
        if (dealer.isBust() || (playerTotal > dealerTotal)) {
            return MatchResult.WIN;
        }
        if (playerTotal == dealerTotal) {
            return MatchResult.PUSH;
        }
        return MatchResult.LOSE;
    }

    public int calculateProfit(BettingMoney bettingMoney) {
        return (int) (bettingMoney.amount() * profitRate);
    }
}
