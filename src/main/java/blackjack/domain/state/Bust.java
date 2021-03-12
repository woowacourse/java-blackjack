package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public class Bust implements State {
    private final Hand hand;

    public Bust(Hand hand) {
        this.hand = hand;
    }

    @Override
    public Hand hand() {
        return hand;
    }

    @Override
    public State draw(Card card) {
        hand.add(card);
        return new Bust(hand);
    }
}
