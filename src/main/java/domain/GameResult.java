package domain;

public enum GameResult {
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

    public GameResult opposite() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
