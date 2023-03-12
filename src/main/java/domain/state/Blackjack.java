package domain.state;

import domain.card.Hand;

public final class Blackjack extends Finished{
    public Blackjack(Hand hand) {
        super(hand);
    }
}
