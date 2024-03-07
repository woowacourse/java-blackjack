package blackjack.domain;

public enum HandResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    HandResult(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public HandResult getOpposite() {
        if (WIN.equals(this)) {
            return LOSE;
        }
        if (LOSE.equals(this)) {
            return WIN;
        }
        return DRAW;
    }
}
