package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum PlayerState {

    BLACKJACK(1.5, "승"),
    WIN(1, "승"),
    LOSE(-1, "패"),
    TIE(0, "무");

    private final double multiple;
    private final String description;

    PlayerState(double multiple, String description) {
        this.multiple = multiple;
        this.description = description;
    }

    public static PlayerState of(Player player, Dealer dealer) {
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return BLACKJACK;
        }
        if (player.isBusted()) {
            return LOSE;
        }
        if (dealer.isBusted()) {
            return WIN;
        }
        return decidePlayerStateByScore(player, dealer);
    }

    private static PlayerState decidePlayerStateByScore(Player player, Dealer dealer) {
        if (player.isBiggerThan(dealer)) {
            return WIN;
        }
        if (dealer.isBiggerThan(player)) {
            return LOSE;
        }
        return TIE;
    }

    public double getMultiple() {
        return multiple;
    }

    public String getDescription() {
        return description;
    }
}
