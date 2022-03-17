package domain;

import domain.card.CardState;
import domain.card.Cards;

public enum GameResult {
    WIN,
    DRAW,
    LOSE,
    ;

    public static GameResult getPlayerResult(final Cards playerCards, final Cards dealerCards) {
        final CardState playerCardsState = playerCards.getCardState();
        final CardState dealerCardsState = dealerCards.getCardState();

        if (playerCardsState.isStand() && dealerCardsState.isStand()) {
            return getPlayerResult(playerCards.calculateSum(), dealerCards.calculateSum());
        }
        return getPlayerResult(playerCardsState, dealerCardsState);
    }

    private static GameResult getPlayerResult(final int playerCardSum, final int dealerCardSum) {
        if (playerCardSum > dealerCardSum) {
            return WIN;
        }
        if (playerCardSum < dealerCardSum) {
            return LOSE;
        }
        return DRAW;
    }

    private static GameResult getPlayerResult(final CardState playerCardsState, final CardState dealerCardsState) {
        if ((playerCardsState.isBlackJack() && dealerCardsState.isBlackJack())) {
            return DRAW;
        }
        if (playerCardsState.isBlackJack() || playerCardsState.isStand() && dealerCardsState.isBust()) {
            return WIN;
        }
        return LOSE;
    }

    private static GameResult reverseFrom(final GameResult origin) {
        if (origin == WIN) {
            return LOSE;
        }
        if (origin == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public static GameResult getDealerResult(final Cards dealerCards, final Cards playerCards) {
        return reverseFrom(getPlayerResult(playerCards, dealerCards));
    }
}
