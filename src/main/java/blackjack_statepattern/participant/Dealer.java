package blackjack_statepattern.participant;

import blackjack_statepattern.card.Card;

public final class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";
    private static final int MIN_SCORE = 17;

    public Dealer() {
        super(DEALER_NAME);
    }

    public Card getOneCard() {
        return state.cards().getOneCard();
    }

    public boolean isRequiredMoreCard() {
        return state.score() < MIN_SCORE;
    }
}
