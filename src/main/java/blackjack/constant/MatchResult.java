package blackjack.constant;

public enum MatchResult {
    WIN("승"), DRAW("무"), LOSE("패"),
    ;

    private final String message;

    MatchResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
