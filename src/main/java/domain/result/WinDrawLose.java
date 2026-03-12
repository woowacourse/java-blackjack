package domain.result;

public enum WinDrawLose {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String description;

    WinDrawLose(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }
}
