package blackjackGame;

public enum Result {
    WIN("승"),
    TIE("무"),
    LOSE("패");

    private final String label;

    Result(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
