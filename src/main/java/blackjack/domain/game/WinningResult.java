package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum WinningResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    NONE("NONE");

    private String result;

    WinningResult(String result) {
        this.result = result;
    }

    public static WinningResult of(Player player, Dealer dealer, boolean isBlackjack) {
        if (isBlackjack) {
            return DRAW.getInitialResult(player, dealer);
        }
        if (player.isBurst()) {
            return LOSE;
        }
        if (dealer.isBurst()) {
            return WIN;
        }
        if (!dealer.isBurst() && (player.getScore() == dealer.getScore())) {
            return DRAW;
        }
        if (!dealer.isBurst() && (player.getScore() > dealer.getScore())) {
            return WIN;
        }
        return LOSE;
    }

    private WinningResult getInitialResult(Player player, Dealer dealer) {
        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return LOSE;
        }
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return DRAW;
        }
        if (player.isBlackjack()) {
            return WIN;
        }
        return NONE;
    }

    public String getResult() {
        return result;
    }
}
