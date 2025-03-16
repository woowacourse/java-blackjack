package blackjack.constant;

public enum MatchResult {

    WIN("승"),
    LOSE("패"),
    PUSH("무"),
    BLACKJACK_WIN("블랙잭 승")
    ;

    private final String message;

    MatchResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
