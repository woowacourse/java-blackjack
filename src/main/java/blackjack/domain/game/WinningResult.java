package blackjack.domain.game;

public enum WinningResult {
    WIN("승"),
    TIE("무"),
    LOSE("패");

    private final String label;

    WinningResult(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
