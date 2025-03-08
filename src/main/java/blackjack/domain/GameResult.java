package blackjack.domain;

import blackjack.domain.player.Player;

public enum GameResult {

    WIN,
    LOSE,
    DRAW;

    public static GameResult getGameResult(Player dealer, Player player) {
        if (isPlayerLoser(dealer, player)) {
            return GameResult.LOSE;
        }
        if (isPlayerWinner(dealer, player)) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    private static boolean isPlayerWinner(Player dealer, Player player) {
        if (dealer.isPlayerNotBust() && player.isPlayerNotBust()) {
            return dealer.calculateCardNumber() < player.calculateCardNumber();
        }
        return dealer.isPlayerBust() && player.isPlayerNotBust();
    }

    private static boolean isPlayerLoser(Player dealer, Player player) {
        if (dealer.isPlayerNotBust() && player.isPlayerNotBust()) {
            return dealer.calculateCardNumber() > player.calculateCardNumber();
        }
        return dealer.isPlayerNotBust() && player.isPlayerBust();
    }
}

