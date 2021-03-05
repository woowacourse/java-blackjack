package blackjack.domain.card;

public enum GameResult {

    NONE("NONE"),
    DRAW("무승부"),
    WIN("승리"),
    LOSE("패배");

    private final String name;

    GameResult(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
