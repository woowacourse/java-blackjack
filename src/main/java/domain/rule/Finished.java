package domain.rule;

import domain.card.Card;
import domain.card.Hand;

public abstract class Finished extends Started {
    public Finished(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException("[ERROR] 게임이 이미 종료되었습니다.");
    }

    @Override
    public State stay() {
        throw new IllegalStateException("[ERROR] 게임이 이미 종료되었습니다.");
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double profit(double betAmount, State dealerState) {
        return betAmount * earningRate(dealerState);
    }

    public abstract double earningRate(State dealerState);
}
