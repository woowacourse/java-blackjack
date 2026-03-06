package blackjack;

public enum GameResult {
    WIN("승"),
    TIE("무"),
    LOSE("패")
    ;

    private final String name;

    GameResult(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
