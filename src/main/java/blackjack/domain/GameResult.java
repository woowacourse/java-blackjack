package blackjack.domain;

public enum GameResult {
    
    WIN("승", 1),
    DRAW("무", 0),
    LOSE("패", 1),
    ;
    
    private final String message;
    private final int score;
    
    GameResult(String message, int score) {
        this.message = message;
        this.score = score;
    }
    
    public String getMessage() {
        return message;
    }
}
