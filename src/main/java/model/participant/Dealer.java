package model.participant;

import model.card.Card;

public final class Dealer extends Participant {
    private static final int HIT_THRESHOLD = 16;

    public Dealer(final Card firstCard, final Card secondCard) {
        super(firstCard, secondCard);
    }

    @Override
    public boolean canHit() {
        return cardDeck.calculateHand() <= HIT_THRESHOLD;
    }

}
