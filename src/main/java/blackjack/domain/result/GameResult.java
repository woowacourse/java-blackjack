package blackjack.domain.result;

import blackjack.domain.betting.BettingMoney;
import blackjack.domain.betting.Profit;
import blackjack.domain.hand.Score;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum GameResult {

    BLACKJACK("블랙잭", 1.5),
    WIN("승", 1.0),
    DRAW("무", 0.0),
    LOSE("패", -1.0),
    ;

    private final String displayName;
    private final double profitMultiplier;

    GameResult(final String displayName, final double profitMultiplier) {
        this.displayName = displayName;
        this.profitMultiplier = profitMultiplier;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Profit calculateProfit(final BettingMoney bettingMoney) {
        return new Profit((int) (bettingMoney.getAmount() * profitMultiplier));
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
