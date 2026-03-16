package domain.state;

import domain.Result;
import domain.Score;
import domain.card.Card;
import domain.card.Hand;

public abstract class State {
    protected final Hand hand;

    public State(Hand hand) {
        this.hand = hand;
    }

    public abstract State draw(Card card);

    public abstract State stay();

    public abstract boolean isFinished();

    public Result judge(State state) {
        if (state instanceof Blackjack) {
            return Result.LOSE;
        }
        if (state instanceof Bust) {
            return Result.WIN;
        }
        return judgeByScore(state);
    }

    private Result judgeByScore(State state) {
        Score sum = hand.totalSum();
        Score targetSum = state.hand.totalSum();

        if (sum.isEqualTo(targetSum)) {
            return Result.DRAW;
        }
        if (sum.isGreaterThan(targetSum)) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    public Hand getHand() {
        return hand;
    }
}
