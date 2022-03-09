package blackjack;

public class Dealer {
    private final Cards cards;

    public Dealer(Cards cards) {
        this.cards = cards;
    }

    Result judge(Cards cards) {
        if (this.cards.score().isBust() && cards.score().isBust()) {
            return Result.WIN;
        }

        return judge(this.cards.score(), cards.score());
    }

    public Result judge(Score score, Score other) {
        if (hasBust(score, other)) {
            return compareBustCase(score, other);
        }
        return compareNormalCase(score, other);
    }

    private boolean hasBust(Score score, Score other) {
        return score.isBust() || other.isBust();
    }

    private Result compareBustCase(Score score, Score other) {
        if (score.isBust() && other.isBust()) {
            return Result.DRAW;
        } else if (score.isBust()) {
            return Result.LOSS;
        }
        return Result.WIN;
    }

    private Result compareNormalCase(Score score, Score other) {
        if (score.compareTo(other) == -1) {
            return Result.LOSS;
        } else if(score.compareTo(other) == 0) {
            return Result.DRAW;
        }
        return Result.WIN;
    }

}
