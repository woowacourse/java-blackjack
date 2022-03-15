package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum Judgement {

    BLACKJACK(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(1);

    private final double profitMultiple;

    Judgement(double profitMultiple) {
        this.profitMultiple = profitMultiple;
    }

    public static Judgement judgePlayer(Player player, Dealer dealer) {
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return BLACKJACK;
        }
        if (player.isBust() || (player.calculateScore() < dealer.calculateScore() && !dealer.isBust())) {
            return LOSE;
        }
        if (dealer.isBust() || player.calculateScore() > dealer.calculateScore()) {
            return WIN;
        }
        return DRAW;
    }

    public double getProfitMultiple() {
        return profitMultiple;
    }
}
