package domain.participant;

import domain.card.Deck;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int STAY_LOWER_BOUND = 16;

    private Dealer(Deck deck) {
        super(DEALER_NAME, deck);
    }

    public static Dealer from(Deck deck) {
        return new Dealer(deck);
    }

    @Override
    public boolean isStand() {
        return calculateScore() > STAY_LOWER_BOUND;
    }
}
