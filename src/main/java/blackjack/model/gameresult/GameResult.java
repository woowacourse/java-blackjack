package blackjack.model.gameresult;

import blackjack.model.user.Dealer;
import blackjack.model.user.Player;

public enum GameResult {

    BLACKJACK_WIN,
    WIN,
    DRAW,
    LOSE,
    ;

    public static GameResult judge(Dealer dealer, Player player) {
        if (player.isBust() || dealer.isBust()) {
            return judgeByBust(player);
        }

        if (player.isBlackjack() || dealer.isBlackjack()) {
            return judgeByBlackjack(dealer, player);
        }

        return judgeByScore(dealer, player);
    }

    private static GameResult judgeByBust(Player player) {
        if (player.isBust()) {
            return LOSE;
        }

        return WIN;
    }

    private static GameResult judgeByBlackjack(Dealer dealer, Player player) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return DRAW;
        }

        if (player.isBlackjack()) {
            return BLACKJACK_WIN;
        }

        return LOSE;
    }

    private static GameResult judgeByScore(Dealer dealer, Player player) {
        if (player.totalScore() > dealer.totalScore()) {
            return WIN;
        }

        if (player.totalScore() < dealer.totalScore()) {
            return LOSE;
        }

        return DRAW;
    }
}