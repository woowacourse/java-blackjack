package domain.participant;

import domain.card.Card;

public class Dealer extends Participant {
    private static final int HIT_THRESHOLD = 16;
    private static final String DEFAULT_NAME = "딜러";

    public Dealer() {
        super(DEFAULT_NAME);
    }

    @Override
    public boolean canHit() {
        return super.canHit() && getScore() <= HIT_THRESHOLD;
    }

    public Card getInitialCard() {
        return getCards().getFirst();
    }
}
