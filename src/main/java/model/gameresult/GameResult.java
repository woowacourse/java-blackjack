package model.gameresult;

import model.card.Cards;

public enum GameResult {
    BLACKJACK_WIN,
    WIN,
    LOSE,
    DRAW;

    public static GameResult createFromCards(final Cards dealerCards, final Cards playerCards) {
        if (playerCards.isBust()) {
            return GameResult.LOSE;
        }
        if (dealerCards.isBust()) {
            return GameResult.WIN;
        }

        GameResult blackjackResult = checkBlackjack(dealerCards, playerCards);
        if (blackjackResult != null) {
            return blackjackResult;
        }

        return compareScores(dealerCards.calculateResult(), playerCards.calculateResult());
    }

    private static GameResult checkBlackjack(final Cards dealerCards, final Cards playerCards) {
        boolean playerBlackjack = playerCards.isBlackjack();
        boolean dealerBlackjack = dealerCards.isBlackjack();

        if (playerBlackjack && dealerBlackjack) {
            return GameResult.DRAW;
        }
        if (playerBlackjack) {
            return GameResult.BLACKJACK_WIN;
        }
        if (dealerBlackjack) {
            return GameResult.LOSE;
        }

        return null;
    }

    private static GameResult compareScores(int dealerScore, int playerScore) {
        if (playerScore > dealerScore) {
            return GameResult.WIN;
        }
        if (playerScore < dealerScore) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }
}