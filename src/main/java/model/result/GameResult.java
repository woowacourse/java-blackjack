package model.result;

import model.cards.Cards;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    SPECIAL_WIN("승");

    private final String name;

    GameResult(final String name) {
        this.name = name;
    }

    public static GameResult getOppositeResult(final GameResult gameResult) {
        if (WIN.equals(gameResult)) {
            return LOSE;
        }
        if (LOSE.equals(gameResult)) {
            return WIN;
        }
        return DRAW;
    }

    public static GameResult determineGameResult(final Cards dealerCards, final Cards playerCards) {
        return determineByBlackjack(dealerCards, playerCards);
    }

    private static GameResult determineByBlackjack(final Cards dealerCards, final Cards playerCards) {
        if (playerCards.isBlackjack() && !dealerCards.isBlackjack()) {
            return SPECIAL_WIN;
        }
        if (!playerCards.isBlackjack() && dealerCards.isBlackjack()) {
            return LOSE;
        }
        return determineByBust(dealerCards, playerCards);
    }

    private static GameResult determineByBust(final Cards dealerCards, final Cards playerCards) {
        if (playerCards.isBust()) {
            return LOSE;
        }
        if (dealerCards.isBust()) {
            return WIN;
        }
        return determineByScore(dealerCards, playerCards);
    }

    private static GameResult determineByScore(final Cards dealerCards, final Cards playerCards) {
        if (playerCards.calculateResult() > dealerCards.calculateResult()) {
            return WIN;
        }
        if (playerCards.calculateResult() < dealerCards.calculateResult()) {
            return LOSE;
        }
        return DRAW;
    }

    public String getName() {
        return name;
    }
}
