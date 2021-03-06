package blackjack.domain.card;

public enum CompeteResult {
    WIN("승"), DRAW("무"), DEFEAT("패");
    
    private final String competeResult;
    
    CompeteResult(String competeResult) {
        this.competeResult = competeResult;
    }
    
    public String getCompeteResult() {
        return competeResult;
    }
}
