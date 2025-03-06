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
        int dealerScore = dealer.getScore();
        int playerScore = player.getScore();

        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }
        return LOSE;
    }

    public static BlackjackResult getOpposite(BlackjackResult result) {
        if (result == WIN) {
            return LOSE;
        }
        if (result == DRAW) {
            return DRAW;
        }
        return WIN;
    }

    public String getValue() {
        return value;
    }
}
