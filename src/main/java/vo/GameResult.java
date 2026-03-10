package vo;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    PUSH("승"),
    BUST("패");

    private final String name;

    GameResult(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public GameResult opposite() {
        if (this == LOSE) return WIN;
        if (this == WIN) return LOSE;
        if (this == BUST) return WIN;
        return this;
    }
}
