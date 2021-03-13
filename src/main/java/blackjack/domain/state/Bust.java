package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public class Bust extends Started {
    public Bust(Hand hand) {
        this.hand = hand;
    }

    @Override
    public State draw(Card card) {
        hand.add(card);
        return new Bust(hand);
    }
}
