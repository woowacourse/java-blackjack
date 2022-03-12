package blackjack.domain;

public enum Outcome {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    Outcome(String name) {
        this.name = name;
    }

    public Outcome getOpposite() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getName() {
        return name;
    }
}
