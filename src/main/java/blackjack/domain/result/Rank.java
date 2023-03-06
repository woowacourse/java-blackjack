package blackjack.domain.result;

public enum Rank {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String label;

    Rank(final String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
