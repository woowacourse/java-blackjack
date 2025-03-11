package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String status;

    GameResult(String status) {
        this.status = status;
    }

    public static GameResult checkDealerWin(final Player player, final Dealer dealer) {

        if (player.isBust()) {
            return GameResult.WIN;
        }
        if (dealer.isBust()) {
            return GameResult.LOSE;
        }

        if(player.isBlackjack() && dealer.isBlackjack()) {
            return GameResult.DRAW;
        }

        if(player.isBlackjack()) {
            return GameResult.LOSE;
        }
        if(dealer.isBlackjack()) {
            return GameResult.WIN;
        }

        if (dealer.getScore().isHigherThan(player.getScore())) {
            return GameResult.WIN;
        }
        if (player.getScore().isHigherThan(dealer.getScore())) {
            return GameResult.LOSE;
        }

        return GameResult.DRAW;
    }

    public static GameResult checkPlayerWin(final Player player, final Dealer dealer) {
        GameResult gameResult = checkDealerWin(player, dealer);

        if (gameResult == GameResult.WIN) {
            return GameResult.LOSE;
        }
        if (gameResult == GameResult.LOSE) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    public String getStatus() {
        return status;
    }
}
