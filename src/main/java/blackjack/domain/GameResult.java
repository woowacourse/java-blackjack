package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum GameResult {
    WIN,
    DRAW,
    LOSE;

    public static GameResult getPlayerGameResultFrom(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return DRAW;
        }
        if (player.isBlackjack() || dealer.isBust()) {
            return WIN;
        }
        return getGameResultByScore(dealer, player);
    }

    private static GameResult getGameResultByScore(final Dealer dealer, final Player player) {
        int dealerSum = dealer.calculateDenominations();
        int playerSum = player.calculateDenominations();

        if (dealerSum < playerSum) {
            return WIN;
        }
        if (dealerSum == playerSum) {
            return DRAW;
        }
        return LOSE;
    }
}
