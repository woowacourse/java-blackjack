package utils;

import domain.participant.Dealer;
import domain.participant.Player;

public class MatchCondition {

    private MatchCondition() {
    }

    public static boolean isPlayerLose(Dealer dealer, Player player) {
        return player.isBust() || (!dealer.isBust() && player.getFinalScore() < dealer.getFinalScore());
    }

    public static boolean isPlayerDraw(Dealer dealer, Player player) {
        return !(player.isBust() || dealer.isBust()) && (player.getFinalScore() == dealer.getFinalScore());
    }

    public static boolean isPlayerBlackjackWin(Dealer dealer, Player player) {
        return !dealer.isBlackjack() && !isPlayerLose(dealer, player) && player.isBlackjack();
    }
}
