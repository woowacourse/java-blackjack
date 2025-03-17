package domain;

import java.util.List;

public abstract class Finished implements State {

    protected final Hand hand;

    protected Finished(Hand hand) {
        this.hand = hand;
    }

    @Override
    public final boolean canHit() {
        return false;
    }

    @Override
    public final State draw(TrumpCard card) {
        throw new IllegalStateException("이미 끝난 상태에서는 카드를 뽑을 수 없습니다.");
    }

    @Override
    public final List<TrumpCard> retrieveCards() {
        return hand.getCards();
    }

    @Override
    public final Score calculateScore() {
        return hand.calculateScore();
    }
}
