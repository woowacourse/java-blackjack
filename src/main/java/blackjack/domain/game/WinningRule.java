package blackjack.domain.game;

import blackjack.domain.card.Score;

public class WinningRule {

    private WinningRule() {
    }

    public static WinOrLose calculateScores(final Score s1, final Score s2) {
        if (s1.isBust() && s2.isBust()) {
            return WinOrLose.DRAW;
        }

        if (s1.isBust() && !s2.isBust()) {
            return WinOrLose.LOSE;
        }

        if (!s1.isBust() && s2.isBust()) {
            return WinOrLose.WIN;
        }

        if (s1.isHigher(s2)) {
            return WinOrLose.WIN;
        }

        if (s1.isLower(s2)) {
            return WinOrLose.LOSE;
        }

        return WinOrLose.DRAW;
    }
}
