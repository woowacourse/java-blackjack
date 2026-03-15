package blackjack.domain.game;

public enum GameResult {
    WIN("승", 1.0),
    BACKJACK_WIN("블랙잭 승", 1.5),
    TIE("무", 0.0),
    LOSE("패", -1.0);

    private final String name;
    private final double multiplier;

    GameResult(String name, double multiplier) {
        this.name = name;
        this.multiplier = multiplier;
    }

    public String getName() {
        return name;
    }

    public int calculateIncome(int betAmount) {
        return (int) (multiplier * betAmount);
    }
}
