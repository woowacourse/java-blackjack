package domain.result;

import static domain.BlackjackGame.BLACKJACK_SCORE;

import domain.participant.Dealer;
import domain.participant.Player;

public enum BlackjackResult {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String value;

    BlackjackResult(String value) {
        this.value = value;
    }

    public static BlackjackResult getPlayerResult(Dealer dealer, Player player) {
        if (isPlayerLose(player, dealer)) {
            return LOSE;
        }
        if (isPlayerWin(player, dealer)) {
            return WIN;
        }
        return DRAW;
    }

    private static boolean isPlayerLose(Player player, Dealer dealer) {
        if (isBust(player.getScore())) {
            return true;
        }
        if (isBlackjack(dealer.getScore(), dealer.getCardCount()) &&
                !isBlackjack(player.getScore(), player.getCardCount())) {
            return true;
        }
        return !isBust(dealer.getScore()) && player.getScore() < dealer.getScore();
    }

    private static boolean isPlayerWin(Player player, Dealer dealer) {
        if (isBust(dealer.getScore())) {
            return true;
        }
        if (isBlackjack(player.getScore(), player.getCardCount()) &&
                !isBlackjack(dealer.getScore(), dealer.getCardCount())) {
            return true;
        }
        return player.getScore() > dealer.getScore();
    }

    private static boolean isBust(int score) {
        return score > BLACKJACK_SCORE;
    }

    private static boolean isBlackjack(int score, int cardCount) {
        return score == BLACKJACK_SCORE && cardCount == 2;
    }

    public String getValue() {
        return value;
    }
}
