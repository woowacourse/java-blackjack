package blackjack.domain.state;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum State {

    WIN(1),
    LOSE(-1),
    BLACKJACK(1.5);

    private final double mul;

    State(double mul) {
        this.mul = mul;
    }

    public static State from(Player player, Dealer dealer) {
        if (player.isBlackjack()) {
            return BLACKJACK;
        }
        if (player.winsAgainst(dealer)) {
            return WIN;
        }
        return LOSE;
    }

    public double apply(int amount) {
        return mul * amount;
    }

}
