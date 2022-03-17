package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum Judgement {

    BLACKJACK(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0);

    private final double profitMultiple;

    Judgement(double profitMultiple) {
        this.profitMultiple = profitMultiple;
    }

    public static Judgement judgePlayer(Player player, Dealer dealer) {
        if (isPlayerBlackjack(player, dealer)) {
            return BLACKJACK;
        }
        if (isPlayerLose(player, dealer)) {
            return LOSE;
        }
        if (isPlayerWin(player, dealer)) {
            return WIN;
        }
        return DRAW;
    }

    private static boolean isPlayerBlackjack(Player player, Dealer dealer) {
        return player.isBlackjack() && !dealer.isBlackjack();
    }

    private static boolean isPlayerLose(Player player, Dealer dealer) {
        return player.isBust() || (player.calculateScore() < dealer.calculateScore() && !dealer.isBust());
    }

    private static boolean isPlayerWin(Player player, Dealer dealer) {
        return dealer.isBust() || player.calculateScore() > dealer.calculateScore();
    }

    public double getProfitMultiple() {
        return profitMultiple;
    }
}
