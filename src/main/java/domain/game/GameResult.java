package domain.game;

public enum GameResult {

    WIN("승", 1.0),
    PUSH("무", 0.0),
    DEFEAT("패", -1.0),
    BLACKJACK_WIN("블랙잭승", 1.5);

    private final String info;
    private final Double yield;

    GameResult(String info, Double yield) {
        this.info = info;
        this.yield = yield;
    }

    public String getInfo() {
        return info;
    }

    public Double getYield() {
        return yield;
    }
}
