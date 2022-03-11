package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum WinningResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private String result;

    WinningResult(String result) {
        this.result = result;
    }

    public static WinningResult of(Player player, Dealer dealer) {
        if (dealer.isBust() && player.isBust() || dealer.getScore() == player.getScore()) {
            return DRAW;
        }
        if (dealer.isBust() && !player.isBust() ||
            !dealer.isBust() && !player.isBust() && (player.getScore() > dealer.getScore())) {
            return WIN;
        }
        return LOSE;
    }

    public String getResult() {
        return result;
    }


}
