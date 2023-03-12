package domain.state;

import domain.card.Hand;

public final class Bust extends Finished{
    public Bust(Hand hand) {
        super(hand);
    }
}
