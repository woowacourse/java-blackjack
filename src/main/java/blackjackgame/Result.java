package blackjackgame;

public enum Result {
    BLACKJACK("승", 2.5),
    WIN("승", 2),
    TIE("무", 1),
    LOSE("패", -2);

    private final String label;
    private final double rate;

    Result(String label, double rate) {
        this.label = label;
        this.rate = rate;
    }

    public String getLabel() {
        return label;
    }

    public double getRate() {
        return rate;
    }
}
