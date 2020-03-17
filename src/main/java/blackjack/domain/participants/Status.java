package blackjack.domain.participants;

public enum Status {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String value;
    private Status opposite;

    static {
        WIN.opposite = LOSE;
        DRAW.opposite = DRAW;
        LOSE.opposite = WIN;
    }

    Status(final String value) {
        this.value = value;
    }

    public Status getOpposite() {
        return opposite;
    }

    @Override
    public String toString() {
        return value;
    }
}
