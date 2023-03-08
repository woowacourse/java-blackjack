package domain.player;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");
    
    private final String symbol;
    
    GameResult(String symbol) {
        this.symbol = symbol;
    }
    
    public String getSymbol() {
        return symbol;
    }
}
