package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public class Hit implements State {
    private final Hand hand;

    public Hit(Hand hand) {
        this.hand = hand;
    }

    @Override
    public Hand hand() {
        return hand;
    }

    @Override
    public State draw(Card card) {
        hand.add(card);
        if (hand.isBust()) {
            return new Bust(hand);
        }
        return new Hit(hand);
    }
}
