package object.game;

public enum GameResult {
    BLACKJACK_WIN("블랙잭 승", 2.5),
    WIN("승", 2.0),
    LOSE("패", 0.0),
    DRAW("무", 1.0)
    ;

    private final String name;
    private final double betRate;

    GameResult(final String name, final double betRate) {
        this.name = name;
        this.betRate = betRate;
    }

    public String getName() {
        return name;
    }

    public double getBetRate() {
        return betRate;
    }
}
