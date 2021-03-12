package blackjack.domain.gametable;

public enum Outcome {
    WIN("승", 1.5),
    LOSE("패", 0.0),
    DRAW("무", 1.0);

    private final String word;
    private final double earningRate;

    Outcome(String word, double earningRate) {
        this.word = word;
        this.earningRate = earningRate;
    }

    public String getWord() {
        return word;
    }

    public double getEarningRate(){
        return earningRate;
    }

}
