package domain.participant;

import domain.card.Card;

public class Dealer extends Participant {
    private static final int HIT_THRESHOLD = 16;

    public Dealer(String name) {
        super(name);
    }

    @Override
    public boolean canHit() {
        return super.canHit() && getScore() <= HIT_THRESHOLD;
    }

    public Card getInitialCard() {
        return getCards().getFirst();
    }
}
