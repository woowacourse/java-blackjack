package domain.player;

import domain.deck.Deck;

public final class Dealer extends Participant {

    private static final int STOP_LOWER_BOUND = 17;

    public Dealer() {
        super(new Hand());
    }

    public void hitTwice(final Deck deck) {
        hit(deck.popCard());
        hit(deck.popCard());
    }

    public boolean shouldHit() {
        return getScore() < STOP_LOWER_BOUND;
    }
}
