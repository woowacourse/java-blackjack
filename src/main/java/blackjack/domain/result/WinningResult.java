package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum WinningResult {

    BLACKJACK("블랙잭"),
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String result;

    WinningResult(String result) {
        this.result = result;
    }

    public static WinningResult of(Player player, Dealer dealer) {
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return BLACKJACK;
        }
        if (dealer.isBust() && !player.isBust() ||
            !dealer.isBust() && !player.isBust() && player.isWin(dealer)) {
            return WIN;
        }
        if (!player.isBust() && player.isDraw(dealer)) {
            return DRAW;
        }
        return LOSE;
    }

    public WinningResult convertResult() {
        if (this == DRAW) {
            return this;
        }
        if (this == LOSE) {
            return WIN;
        }
        return LOSE;
    }

    public boolean isBlackjack() {
        return this == BLACKJACK;
    }

    public boolean isWin() {
        return this == WIN;
    }

    public boolean isLose() {
        return this == LOSE;
    }

    public String getResult() {
        return result;
    }

}
