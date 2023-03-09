package blackjackgame;

public enum Result {
    BLACKJACK("승", 1.5),
    WIN("승", 1),
    TIE("무", 0),
    LOSE("패", -1);

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
