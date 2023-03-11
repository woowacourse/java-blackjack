package domain.state;

import domain.card.Card;
import domain.user.Hand;

import java.util.List;

public class Bust implements State {

    private final Hand hand;

    public Bust(Hand hand) {
        this.hand = hand;
    }

    @Override
    public State draw(final Card card) {
        throw new IllegalStateException("버스트에서는 카드를 뽑을 수 없는 상태");
    }

    @Override
    public List<Card> cards() {
        return hand.getCards();
    }
}
