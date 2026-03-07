package domain.game;

public enum GameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private String gameResult;

    GameResult(String gameResult) {
        this.gameResult = gameResult;
    }

    public static GameResult determine(int dealerScore, int gamblerScore) {
        if(dealerScore < gamblerScore) {
            return WIN;
        }
        if(dealerScore > gamblerScore) {
            return LOSE;
        }
        return DRAW;
    }

    public String getGameResult() {
        return gameResult;
    }
}
