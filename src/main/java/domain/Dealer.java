package domain;

import java.util.List;

public class Dealer extends Participant {

    public static final String DEALER_DEFAULT_NAME = "딜러";

    public Dealer(Cards cards) {
        super(new ParticipantName(DEALER_DEFAULT_NAME), cards);
    }

    @Override
    public boolean shouldHit() {
        return !cards.isDealerDrawLimitExceeded();
    }

    @Override
    public List<Card> getInitialCards() {
        return List.of(cards.getInitialCard());
    }
}
