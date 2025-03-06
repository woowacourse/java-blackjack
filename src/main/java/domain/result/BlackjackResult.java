package domain.result;

import domain.participant.Participant;

public enum BlackjackResult {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String value;

    BlackjackResult(String value) {
        this.value = value;
    }

    public static BlackjackResult getPlayerResult(Participant dealer, Participant player) {
        if (isPlayerLose(player, dealer)) {
            return LOSE;
        }
        if (isPlayerWin(player, dealer)) {
            return WIN;
        }
        return DRAW;
    }

    private static boolean isPlayerLose(Participant player, Participant dealer) {
        if (isBust(player.getScore())) {
            return true;
        }
        if (isBlackjack(dealer) && !isBlackjack(player)) {
            return true;
        }
        if (!isBust(dealer.getScore()) && player.getScore() < dealer.getScore()) {
            return true;
        }
        return false;
    }

    private static boolean isPlayerWin(Participant player, Participant dealer) {
        if (isBust(dealer.getScore())) {
            return true;
        }
        if (isBlackjack(player) && !isBlackjack(dealer)) {
            return true;
        }
        if (player.getScore() > dealer.getScore()) {
            return true;
        }
        return false;
    }

    private static boolean isBust(int score) {
        return score > 21;
    }

    private static boolean isBlackjack(Participant participant) {
        return participant.getScore() == 21 && participant.getCardCount() == 2;
    }

    public String getValue() {
        return value;
    }
}
