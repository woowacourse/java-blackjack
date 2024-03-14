package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

public enum GameResult {
    WIN,
    LOSE;

    public static GameResult createPlayerResult(Dealer dealer, Player player) {
        if (player.isBust() || isDealerHighScore(dealer, player)) {
            return LOSE;
        }
        return WIN;
    }

    private static boolean isDealerHighScore(Dealer dealer, Player player) {
        return dealer.isNotBust() && player.getScore() <= dealer.getScore();
    }

    public GameResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        return WIN;
    }
}
