package blackjack.domain.result;

import blackjack.domain.betting.BettingMoney;
import blackjack.domain.betting.Profit;
import blackjack.domain.hand.Score;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum GameResult {
    BLACKJACK(3, 2),
    WIN(1, 1),
    DRAW(0, 1),
    LOSE(-1, 1),
    ;

    private final int numerator;
    private final int denominator;

    GameResult(final int numerator, final int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Profit calculateProfit(final BettingMoney bettingMoney) {
        return new Profit(bettingMoney.getAmount() * numerator / denominator);
    }

    public static GameResult of(final Player player, final Dealer dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return DRAW;
        }
        if (player.isBlackjack()) {
            return BLACKJACK;
        }
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return compare(player.calculateScore(), dealer.calculateScore());
    }

    private static GameResult compare(final Score playerScore, final Score dealerScore) {
        if (playerScore.isGreaterThan(dealerScore)) {
            return WIN;
        }
        if (playerScore.isLessThan(dealerScore)) {
            return LOSE;
        }
        return DRAW;
    }
}
