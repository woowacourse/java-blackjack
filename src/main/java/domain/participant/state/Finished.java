package domain.participant.state;

import domain.card.Card;
import domain.card.Hand;

import java.util.List;

public abstract class Finished implements State {
    private final Hand hand;

    public Finished(final Hand hand) {
        this.hand = hand;
    }


    @Override
    public State draw(final Card card) {
        throw new UnsupportedOperationException("종료상태에서는 카드를 뽑을 수 없습니다.");
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("종료상태에서는 스탠드 할 수 없습니다.");
    }

    @Override
    public boolean isHit() {
        return false;
    }

    @Override
    public int score() {
        return hand.score().toInt();
    }

    @Override
    public List<Card> hand() {
        return hand.cards();
    }
}
