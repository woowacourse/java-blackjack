package domain.betting;

import domain.participant.Dealer;
import domain.participant.Player;

public enum MatchResult {
    PLAYER_BUST(-10),
    BOTH_NATURAL_BLACKJACK(0),
    PLAYER_NATURAL(15),
    DEALER_NATURAL(-10),
    PLAYER_WIN(10),
    DRAW(0),
    PLAYER_LOSE(-10);

    private final int profitRate;

    MatchResult(int profitRate) {
        this.profitRate = profitRate;
    }

    public int calculateProfit(BettingAmount bettingAmount) {
        return (this.profitRate * bettingAmount.getValue()) / 10;
    }

    public static MatchResult determine(Dealer dealer, Player player) {
        if (player.isBust()) {
            return PLAYER_BUST;
        }
        if (dealer.isBust()) {
            return PLAYER_WIN;
        }
        if (player.isNatural() && dealer.isNatural()) {
            return BOTH_NATURAL_BLACKJACK;
        }
        if (player.isNatural()) {
            return PLAYER_NATURAL;
        }
        if (dealer.isNatural()) {
            return DEALER_NATURAL;
        }
        if (player.hasScoreHigherThan(dealer)) {
            return PLAYER_WIN;
        }
        if (player.hasScoreSameAs(dealer)) {
            return DRAW;
        }

        return PLAYER_LOSE;
    }
}