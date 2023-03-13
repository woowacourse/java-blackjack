package blackjack.domain.result;

public enum Result {
    BLACKJACK("블랙잭", 1.5),

    WIN("승", 1),
    DRAW("무", 0),
    LOSE("패", -1);

    private final String label;
    private final double rateOfReturn;

    Result(String label, double rateOfReturn) {
        this.label = label;
        this.rateOfReturn = rateOfReturn;
    }

    public String getLabel() {
        return label;
    }

    public double getRateOfReturn() {
        return rateOfReturn;
    }
}
