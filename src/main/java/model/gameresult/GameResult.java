package model.gameresult;

import model.card.Cards;

public enum GameResult {
    BLACKJACK_WIN,
    WIN,
    LOSE,
    DRAW;

    public static GameResult createFromCards(final Cards dealerCards, final Cards playerCards) {
        boolean playerBust = playerCards.isBust();
        boolean dealerBust = dealerCards.isBust();
        boolean playerBlackjack = playerCards.isBlackjack();
        boolean dealerBlackjack = dealerCards.isBlackjack();
        int playerScore = playerCards.calculateResult();
        int dealerScore = dealerCards.calculateResult();

        if (playerBust) {
            return GameResult.LOSE;
        }
        if (dealerBust) {
            return GameResult.WIN;
        }
        if (playerBlackjack && dealerBlackjack) {
            return GameResult.DRAW;
        }
        if (playerBlackjack) {
            return GameResult.BLACKJACK_WIN;
        }
        if (dealerBlackjack) {
            return GameResult.LOSE;
        }
        if (playerScore > dealerScore) {
            return GameResult.WIN;
        }
        if (playerScore < dealerScore) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }
}
