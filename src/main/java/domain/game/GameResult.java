package domain.game;

import static util.Constants.BLACKJACK_NUMBER;

public enum GameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private String gameResult;

    GameResult(String gameResult) {
        this.gameResult = gameResult;
    }

    public static GameResult determine(int dealerScore, int gamblerScore) {
        if(gamblerScore > BLACKJACK_NUMBER) return LOSE;
        if(dealerScore > BLACKJACK_NUMBER) return WIN;

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
