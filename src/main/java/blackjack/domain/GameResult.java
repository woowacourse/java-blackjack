package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum GameResult {

    BLACKJACK_WIN,
    WIN,
    PUSH,
    LOSE;

    public static GameResult judge(Dealer dealer, Player player) {
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return GameResult.BLACKJACK_WIN;
        }
        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return GameResult.LOSE;
        }
        return compareBust(player, dealer);
    }

    private static GameResult compareBust(Player player, Dealer dealer) {
        if (player.isBust()) {
            return GameResult.LOSE;
        }
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        return compareScore(player, dealer);
    }

    private static GameResult compareScore(Player player, Dealer dealer) {
        if (player.hasHigherScore(dealer)) {
            return GameResult.WIN;
        }
        if (dealer.hasHigherScore(player)) {
            return GameResult.LOSE;
        }
        return GameResult.PUSH;
    }
}
