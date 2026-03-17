package domain.state;

import domain.Hand;
import domain.card.Card;

public final class Bust extends Started {

    public Bust(Hand hand) {
        super(hand);
    }

    @Override
    public PlayerState draw(Card card) {
        throw new IllegalStateException("Bust 상태에서는 hit할 수 없습니다.");
    }

    @Override
    public PlayerState onStay() {
        return this;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isBust(){
        return true;
    }
}
