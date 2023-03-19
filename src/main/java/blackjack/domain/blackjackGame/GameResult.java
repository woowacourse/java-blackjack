package blackjack.domain.blackjackGame;

public enum GameResult {
    BLACKJACK("블랙잭"),
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String name;

    GameResult(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
