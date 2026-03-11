package blackjack.domain.participant;

public enum GameResult {
    WIN, DRAW, LOSE;
    
    public GameResult opposite() {
        if (this == WIN) {
            return GameResult.LOSE;
        }
        if (this == LOSE) {
            return GameResult.WIN;
        }
        return DRAW;
    }
}
