package model;

public class Judge {
    public GameResult determineGameResult(final Cards dealerCards, final Cards playerCards) {
        if (playerCards.isBust()) {
            return GameResult.LOSE;
        }
        if (dealerCards.isBust()) {
            return GameResult.WIN;
        }
        if (playerCards.calculateResult() > dealerCards.calculateResult()) {
            return GameResult.WIN;
        }
        if (playerCards.calculateResult() < dealerCards.calculateResult()) {
            return GameResult.LOSE;
        }
        if (playerCards.calculateResult() == dealerCards.calculateResult()) {
            return GameResult.DRAW;
        }
        return GameResult.DRAW;
    }
}
