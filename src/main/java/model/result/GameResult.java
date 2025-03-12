package model.result;

import model.cards.Cards;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

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
        if (playerCards.isBust()) {
            return GameResult.LOSE;
        }
        if (dealerCards.isBust()) {
            return GameResult.WIN;
        }
        return determineGameResultByScore(dealerCards, playerCards);
    }

    private static GameResult determineGameResultByScore(final Cards dealerCards, final Cards playerCards) {
        if (playerCards.calculateResult() > dealerCards.calculateResult()) {
            return GameResult.WIN;
        }
        if (playerCards.calculateResult() < dealerCards.calculateResult()) {
            return GameResult.LOSE;
        }
        return DRAW;
    }

    public String getName() {
        return name;
    }
}
