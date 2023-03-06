package domain.player;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");
    
    private final String gameResult;
    
    GameResult(String gameResult) {
        this.gameResult = gameResult;
    }
    
    public String getGameResult() {
        return gameResult;
    }
}
