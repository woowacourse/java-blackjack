package domain.rule;

import domain.card.Card;
import domain.card.Hand;

public abstract class Running extends Started {
    protected Running(Hand hand) {
        super(hand);
    }

    public abstract State draw(Card card);

    public abstract State stay();

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
        return false;
    }

    @Override
    public double profit(double betAmount, State dealerState) {
        throw new IllegalStateException("[ERROR] 아직 게임이 진행 중입니다.");
    }
}
