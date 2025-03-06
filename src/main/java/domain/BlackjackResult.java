package domain;

public enum BlackjackResult {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String value;

    BlackjackResult(String value) {
        this.value = value;
    }

    public static BlackjackResult getDealerResult(Participant dealer, Participant player) {
        int dealerScore = dealer.getScore();
        int playerScore = player.getScore();

        if (dealerScore > playerScore) {
            return WIN;
        }
        if (dealerScore == playerScore) {
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
}
