package blackjack.domain;

public enum Judgement {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    Judgement(String name) {
        this.name = name;
    }

    public Judgement getOpposite() {
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
