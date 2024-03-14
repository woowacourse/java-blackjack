package blackjack.model.result;

import blackjack.model.card.Score;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;

public enum MatchResult {
    BLACKJACK(1.5),
    WIN(1),
    LOSE(-1),
    PUSH(0);

    private final double earningRate;

    MatchResult(final double earningRate) {
        this.earningRate = earningRate;
    }

    public static MatchResult of(final Player player, final Dealer dealer) {
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return BLACKJACK;
        } else if (player.isBlackJack() && dealer.isBlackJack()) {
            return PUSH;
        } else if (player.isBust()) {
            return LOSE;
        } else if (dealer.isBust()) {
            return WIN;
        }
        return decideByScore(player, dealer);
    }

    private static MatchResult decideByScore(final Player player, final Dealer dealer) {
        Score playerTotalScore = player.calculateCardsTotalScore();
        Score dealerTotalScore = dealer.calculateCardsTotalScore();
        if (playerTotalScore.equalTo(dealerTotalScore)) {
            return PUSH;
        } else if (playerTotalScore.greaterThan(dealerTotalScore)) {
            return WIN;
        }
        return LOSE;
    }

    public BettingMoney calculateFinalMoney(final BettingMoney bettingMoney) {
        return bettingMoney.applyEarningRate(earningRate);
    }
}
