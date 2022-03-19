package blackjack_statepattern.participant;

import blackjack_statepattern.card.Card;

public final class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public Card getOneCard() {
        return super.state.cards().getOneCard();
    }
}
