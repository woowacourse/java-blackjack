package blackjack.domain;

import blackjack.domain.card.Cards;

public enum WinningResult {
    DRAW, WIN, LOSE;

    public static WinningResult decide(Cards mainCards, Cards subCards) {
        if (mainCards.isBust()) {
            if (subCards.isBust()) {
                return WinningResult.DRAW;
            }
            return WinningResult.LOSE;
        }

        if (subCards.isBust()) {
            return WinningResult.WIN;
        }

        if (mainCards.isBlackjack() || subCards.isBlackjack()) {
            if (mainCards.isBlackjack() && subCards.isBlackjack()) {
                return WinningResult.DRAW;
            }
            if (mainCards.isBlackjack()) {
                return WinningResult.WIN;
            }
            return WinningResult.LOSE;
        }

        int mainCardScore = mainCards.calculateMaxScore();
        int subCardScore = subCards.calculateMaxScore();

        if (mainCardScore > subCardScore) {
            return WinningResult.WIN;
        }
        if (mainCardScore < subCardScore) {
            return WinningResult.LOSE;
        }
        return WinningResult.DRAW;
    }
}
