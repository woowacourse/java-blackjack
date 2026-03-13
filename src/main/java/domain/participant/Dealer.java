package domain.participant;

import domain.card.Card;

public class Dealer extends Participant {

    private static final int HIT_THRESHOLD = 17;

    public Dealer() {
    }

    public boolean shouldHit() {
        return getScore() < HIT_THRESHOLD;
    }

    public Card getFirstCard() {
        return getCards().getFirst();
    }
}
