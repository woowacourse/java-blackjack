package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum PlayerWinningResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String result;

    PlayerWinningResult(String result) {
        this.result = result;
    }

    public static PlayerWinningResult of(Dealer dealer, Player player) {
        if ((dealer.isBlackjack() && !player.isBlackjack()) || player.isBust()) {
            return LOSE;
        }
        if ((!dealer.isBlackjack() && player.isBlackjack()) || dealer.isBust()) {
            return WIN;
        }
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return DRAW;
        }
        return getResultWithScore(dealer, player);
    }

    private static PlayerWinningResult getResultWithScore(Dealer dealer, Player player) {
        if (player.getScore() < dealer.getScore()) {
            return LOSE;
        }
        if (player.getScore() > dealer.getScore()) {
            return WIN;
        }
        return DRAW;
    }

    public PlayerWinningResult reverse() {
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
