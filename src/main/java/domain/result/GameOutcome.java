package domain.result;

public enum GameOutcome {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String description;

    GameOutcome(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }
}
