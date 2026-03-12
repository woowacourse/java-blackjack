package blackjack.domain;

public enum GameResult {
    WIN("승", 1),
    TIE("무", 0),
    LOSE("패", -1),
    BLACKJACK_WIN("블랙잭", 1.5);

    private final String name;
    private final double earningRate;

    GameResult(String name, double earningRate) {
        this.name = name;
        this.earningRate = earningRate;
    }

    public String getName() {
        return name;
    }

    public double getEarningRate() {
        return earningRate;
    }
}
