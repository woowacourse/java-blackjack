package domain.state.type;

import domain.deck.Card;
import domain.gamer.Hand;
import domain.state.Finished;
import domain.state.State;

public class Bust extends Finished implements State {

    public Bust(final Hand hand) {
        super(hand);
    }

    @Override
    public State hit(final Card card) {
        throw new IllegalArgumentException("더 이상 카드를 받을 수 없습니다.");
    }

    @Override
    public State stay() {
        throw new IllegalArgumentException("이미 bust입니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
