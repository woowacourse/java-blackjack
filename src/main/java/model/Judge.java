package model;

public class Judge {

    public GameResult determineGameResult(final Cards dealerCards, final Cards playerCards) {
        if (playerCards.isBust()) {
            return GameResult.LOSE;
        }
        return GameResult.WIN;
    }

}
