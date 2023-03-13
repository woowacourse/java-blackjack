package blackjack.domain;

public enum Result {
    LOSE("패", -1),
    DRAW("무", 0),
    WIN("승", 1),
    BLACKJACK("블랙잭", 1.5);

    private final String result;
    private final double rate;

    Result(String result, double rate) {
        this.result = result;
        this.rate = rate;
    }

    public String getResult() {
        return result;
    }

    public double getRate() {
        return rate;
    }
}
