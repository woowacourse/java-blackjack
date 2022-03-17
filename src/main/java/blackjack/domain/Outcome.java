package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum Outcome {
    WIN(1),
    DRAW(0),
    LOSE(-1),
    WIN_BLACKJACK(1.5);

    private final double profitRate;

    Outcome(double profitRate) {
        this.profitRate = profitRate;
    }

    public static Outcome judge(Player player, Dealer dealer) {
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return WIN_BLACKJACK;
        }
        if (player.isBust() || !player.isBlackjack() && dealer.isBlackjack()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return judgeByScore(player.getScore(), dealer.getScore());
    }

    private static Outcome judgeByScore(Score score, Score target) {
        if (score.isGreaterThan(target)) {
            return WIN;
        }
        if (score.equals(target)) {
            return DRAW;
        }
        return LOSE;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
