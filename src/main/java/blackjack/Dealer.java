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
        return judge(cards.score());
    }

    public Result judge(Score other) {
        if (hasBust(other)) {
            return compareBustCase(other);
        }
        return compareNormalCase(other);
    }

    private boolean hasBust(Score other) {
        return score().isBust() || other.isBust();
    }

    private Result compareBustCase(Score other) {
        if (score().isBust() && other.isBust()) {
            return Result.DRAW;
        } else if (score().isBust()) {
            return Result.LOSS;
        }
        return Result.WIN;
    }

    private Result compareNormalCase(Score other) {
        if (score().compareTo(other) == -1) {
            return Result.LOSS;
        } else if(score().compareTo(other) == 0) {
            return Result.DRAW;
        }
        return Result.WIN;
    }

    private Score score() {
        return cards.score();
    }
}
