package blackjack.domain.participant;

import blackjack.domain.card.Card;

public class Dealer extends Participant {

    private static final int HIT_THRESHOLD = 16;

    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        this.name = DEALER_NAME;
    }

    @Override
    public boolean isHittable() {
        return getScore() <= HIT_THRESHOLD;
    }

    @Override
    public String getName() {
        return name;
    }

    public Card getFirstCard() {
        return state.getParticipantCards().getFirstCard();
    }

}
