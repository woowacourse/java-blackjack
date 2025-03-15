package domain.participant;

import domain.card.Cards;

public class Dealer extends Participant {

    private static final String DEALER_DEFAULT_NAME = "딜러";

    public Dealer(Cards cards) {
        super(new ParticipantName(DEALER_DEFAULT_NAME), cards);
    }

    @Override
    public boolean shouldHit() {
        return !cards.isDealerDrawLimitExceeded();
    }
}
