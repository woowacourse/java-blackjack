package domain.state;

public enum Result {
    //여기서 금액 회수 or 1.5배 or 2배
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String displayName;

    Result(String displayName) {
        this.displayName = displayName;
    }
}
