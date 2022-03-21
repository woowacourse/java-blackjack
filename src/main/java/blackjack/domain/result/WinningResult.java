package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum WinningResult {

    BLACKJACK("블랙잭", 1.5),
    WIN("승", 1),
    LOSE("패", -1),
    DRAW("무", 0);

    private final String result;
    private final double earningRate;

    WinningResult(String result, double earningRate) {
        this.result = result;
        this.earningRate = earningRate;
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

    public String getResult() {
        return result;
    }

    public double getEarningRate() {
        return earningRate;
    }

}
