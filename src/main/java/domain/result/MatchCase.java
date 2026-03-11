package domain.result;

public enum MatchCase {
    WIN("승"), DRAW("무"), LOSE("패");

    private final String description;

    MatchCase(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
