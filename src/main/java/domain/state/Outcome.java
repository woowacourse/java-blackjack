package domain.state;

public enum Outcome {
    BLACKJACK_WIN("승", 1.5),
    DEFAULT_WIN("승", 1.0),
    DRAW("무", 0.0),
    LOSE("패", -1.0);

    private final String result;
    private final double winningCoefficient;

    Outcome(String result, double winningCoefficient) {
        this.result = result;
        this.winningCoefficient = winningCoefficient;
    }

    public String result(){
        return result;
    }

    public double winningCoefficient(){
        return winningCoefficient;
    }
}
