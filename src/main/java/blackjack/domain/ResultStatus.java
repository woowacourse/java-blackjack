package blackjack.domain;

public enum ResultStatus {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    ResultStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
