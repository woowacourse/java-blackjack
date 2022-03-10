package blackjack.domain.player;

public enum Outcome {

    WIN("승"),
    LOSE("패")
    ;

    private final String message;

    Outcome(String message) {
        this.message = message;
    }

    public Outcome reverse() {
        if (this == WIN) {
            return LOSE;
        }
        return WIN;
    }

    public String getMessage() {
        return message;
    }
}
