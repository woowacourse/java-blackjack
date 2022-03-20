package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum PlayerResult {

    BLACKJACK_WIN(1.5),
    WIN(1),
    DRAW(0),
    LOSS(-1);

    private final double profitRate;

    PlayerResult(final double profitRate) {
        this.profitRate = profitRate;
    }

    public static PlayerResult of(final Player player, final Dealer dealer) {
        if (player.isBust() || dealer.isBust()) {
            return checkBust(player);
        }

        if (player.isBlackjack() || dealer.isBlackjack()) {
            return checkBlackjack(player, dealer);
        }

        return compareScore(player, dealer);
    }

    private static PlayerResult checkBust(final Player player) {
        if (player.isBust()) {
            return LOSS;
        }

        return WIN;
    }

    private static PlayerResult checkBlackjack(final Player player, final Dealer dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return DRAW;
        }

        if (player.isBlackjack()) {
            return BLACKJACK_WIN;
        }

        return LOSS;
    }

    private static PlayerResult compareScore(final Player player, final Dealer dealer) {
        if (player.hasSameScoreWith(dealer)) {
            return DRAW;
        }

        if (player.hasHigherScoreThan(dealer)) {
            return WIN;
        }

        return LOSS;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
