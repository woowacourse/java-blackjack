package state;

import card.CardHand;

public abstract class Running extends Started {
    public Running(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

//    @Override
//    public Bet profit(final Bet battingAmount) {
//        throw new IllegalArgumentException();
//    }
}
