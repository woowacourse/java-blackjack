package domain.player;

public enum ParticipantGameResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");
    
    private final String symbol;
    
    ParticipantGameResult(String symbol) {
        this.symbol = symbol;
    }
    
    public String getSymbol() {
        return symbol;
    }
}
