package domain.state;

import domain.card.Hand;

public final class Blackjack extends Finished {
    Blackjack(Hand hand) {
        super(hand);
    }
}
