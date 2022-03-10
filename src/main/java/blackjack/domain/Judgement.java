package blackjack.domain;

public enum Judgement {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    Judgement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
