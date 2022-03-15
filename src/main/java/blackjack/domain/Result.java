package blackjack.domain;

public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSE("패");
    
    private final String resultText;
    
    Result(final String resultText) {
        this.resultText = resultText;
    }
    
    @Override
    public String toString() {
        return resultText;
    }
}
