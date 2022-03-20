package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

public enum Outcome {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String outcome;

    Outcome(String outcome) {
        this.outcome = outcome;
    }

    public static Outcome matchAboutPlayer(Dealer dealer, Player player) {
        if (checkWin(dealer, player)) {
            return WIN;
        }
        if (checkLose(dealer, player)) {
            return LOSE;
        }
        return DRAW;
    }

    private static boolean checkWin(Player dealer, Player player) {
        if (!player.isBust() && dealer.isBust()) {
            return true;
        }
        if (!dealer.isBlackjack() && player.isBlackjack()) {
            return true;
        }
        return !player.isBust() && dealer.getScore() < player.getScore();
    }

    private static boolean checkLose(Player dealer, Player player) {
        if (player.isBust()) {
            return true;
        }
        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return true;
        }
        return !dealer.isBust() && dealer.getScore() > player.getScore();
    }

    public String get() {
        return outcome;
    }
}
