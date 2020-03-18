package blackjack.domain;

public enum PlayerResult {
    BLACKJACK_WIN("승", 1.5),
    WIN("승", 1.0),
    LOSE("패", -1.0),
    DRAW("무", 0.0);

    private String koreanName;
    private double bettingRatio;

    PlayerResult(String koreanName, double bettingRatio) {
        this.koreanName = koreanName;
        this.bettingRatio = bettingRatio;
    }

    public int calculateProfit(int bettingMoney) {
        return (int) (this.bettingRatio * bettingMoney);
    }
}
