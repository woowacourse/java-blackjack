package blackjack.domain.result;

public enum Rank {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String label;

    Rank(String label) {
        this.label = label;
    }

    public Rank getOpposite() {
        if (this.equals(Rank.WIN)) {
            return LOSE;
        }
        if (this.equals(Rank.LOSE)) {
            return WIN;
        }
        return DRAW;
    }

    public String getLabel() {
        return label;
    }
}
