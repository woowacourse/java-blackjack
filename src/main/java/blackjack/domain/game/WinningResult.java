package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum WinningResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    NONE("NONE");

    private final String result;

    WinningResult(String result) {
        this.result = result;
    }

    public static WinningResult of(Dealer dealer, Player player) {
        if (dealer.isBlackjack() && !player.isBlackjack()
            || player.isBust()
            || player.getScore() < dealer.getScore()) {
            return LOSE;
        }
        if (!dealer.isBlackjack() && player.isBlackjack() || player.getScore() > dealer.getScore()) {
            return WIN;
        }
        return DRAW;
    }

    public WinningResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return this;
    }

    public String getResult() {
        return result;
    }
}
