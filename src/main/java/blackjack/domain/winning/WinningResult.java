package blackjack.domain.winning;

import blackjack.domain.card.Cards;

public enum WinningResult {
    DRAW, WIN, LOSE;

    public static WinningResult decide(Cards mainCards, Cards subCards) {
        if (mainCards.isBust() && subCards.isBust()) {
            return WinningResult.DRAW;
        }
        if (mainCards.isBust()) {
            return WinningResult.LOSE;
        }
        if (subCards.isBust()) {
            return WinningResult.WIN;
        }

        if (mainCards.isBlackjack() && subCards.isBlackjack()) {
            return WinningResult.DRAW;
        }
        if (mainCards.isBlackjack()) {
            return WinningResult.WIN;
        }
        if (subCards.isBlackjack()) {
            return WinningResult.LOSE;
        }

        return compareScore(mainCards, subCards);
    }

    private static WinningResult compareScore(Cards mainCards, Cards subCards) {
        int mainScore = mainCards.calculateMaxScore();
        int subScore = subCards.calculateMaxScore();
        if (mainScore > subScore) {
            return WinningResult.WIN;
        }
        if (mainScore < subScore) {
            return WinningResult.LOSE;
        }
        return WinningResult.DRAW;
    }

}
