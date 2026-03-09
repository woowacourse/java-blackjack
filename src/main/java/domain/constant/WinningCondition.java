package domain.constant;

public enum WinningCondition {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String description;

    WinningCondition(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }
}
