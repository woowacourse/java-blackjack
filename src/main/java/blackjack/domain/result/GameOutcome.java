package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.math.BigDecimal;

public enum GameOutcome {
    BLACKJACK_WIN(new BigDecimal(1.5)),
    WIN(new BigDecimal(1)),
    DRAW(new BigDecimal(0)),
    LOSE(new BigDecimal(-1)),
    ;

    private final BigDecimal payoutRate;

    GameOutcome(BigDecimal payoutRate) {
        this.payoutRate = payoutRate;
    }

    public BigDecimal getPayoutRate() {
        return payoutRate;
    }

    public static GameOutcome judge(Player player, Dealer dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return DRAW;
        }
        if (player.isBlackjack()) {
            return BLACKJACK_WIN;
        }
        if (dealer.isBlackjack()) {
            return LOSE;
        }

        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }

        if (player.getScore() > dealer.getScore()) {
            return WIN;
        }
        if (player.getScore() < dealer.getScore()) {
            return LOSE;
        }

        return DRAW;
    }

    public GameOutcome reverse() {
        if (this == WIN || this == BLACKJACK_WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
