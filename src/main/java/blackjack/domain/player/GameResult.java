package blackjack.domain.player;

public enum GameResult {

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
