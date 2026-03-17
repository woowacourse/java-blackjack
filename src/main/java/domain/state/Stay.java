package domain.state;

import domain.Hand;
import domain.card.Card;

public final class Stay extends Started {

    public Stay(Hand hand) {
        super(hand);
    }

    @Override
    public PlayerState draw(Card card) {
        throw new IllegalStateException("Stay 상태에서는 hit할 수 없습니다.");
    }

    @Override
    public PlayerState onStay() {
        return this;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
