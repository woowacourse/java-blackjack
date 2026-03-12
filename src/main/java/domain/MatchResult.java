package domain;

public enum MatchResult {
    BLACKJACK("블랙잭", 1.5),
    WIN("승", 1.0),
    LOSE("패", -1.0),
    DRAW("무", 0.0);

    private final String name;
    private final double ratio;

    MatchResult(String name, double ratio) {
        this.name = name;
        this.ratio = ratio;
    }

    public String getName() {
        return name;
    }

    public int calculateIncome(BettingMoney bettingMoney) {
        return (int) (this.ratio * bettingMoney.getMoney());
    }
}
