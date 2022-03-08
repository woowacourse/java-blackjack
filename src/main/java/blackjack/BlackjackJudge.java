package blackjack;

public class BlackjackJudge {

    public static Result judge(Cards cards1, Cards cards2) {
        if (hasBust(cards1, cards2)) {
            return judgeBustCase(cards1, cards2);
        }
        return judgeNormalCase(cards1, cards2);
    }

    private static boolean hasBust(Cards cards1, Cards cards2) {
        return cards1.isBust() || cards2.isBust();
    }

    private static Result judgeBustCase(Cards cards1, Cards cards2) {
        if (cards1.isBust() && cards2.isBust()) {
            return Result.DRAW;
        }

        if (cards1.isBust()) {
            return Result.LOSS;
        }

        return Result.WIN;
    }

    private static Result judgeNormalCase(Cards cards1, Cards cards2) {
        if (cards1.score() > cards2.score()) {
            return Result.WIN;
        }

        if (cards1.score() == cards2.score()) {
            return Result.DRAW;
        }

        return Result.LOSS;
    }
}
