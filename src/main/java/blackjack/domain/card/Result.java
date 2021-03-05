package blackjack.domain.card;

public enum Result {

    NONE("NONE"),
    DRAW("무승부"),
    WIN("승리"),
    LOSE("패배");

    private final String name;

    Result(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
