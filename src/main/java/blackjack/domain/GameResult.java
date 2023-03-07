package blackjack.domain;

public enum GameResult {
    WIN("승", "패"),
    LOSE("패", "승"),
    DRAW("무", "무");

    private final String name;
    private final String antonym;

    GameResult(String name, String antonym) {
        this.name = name;
        this.antonym = antonym;
    }

    public String getName() {
        return name;
    }

    public String getAntonym() {
        return antonym;
    }
}
