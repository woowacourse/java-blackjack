package blackjack.domain;

public enum ResultType {
    WIN("승", 1),
    DRAW("무", 0),
    LOSE("패", -1);

    private final String message;
    private final int score;

    ResultType(String message, int score) {
        this.message = message;
        this.score = score;
    }

    public String message() {
        return message;
    }

    public int score() {
        return score;
    }
}
