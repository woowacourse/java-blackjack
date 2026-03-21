package domain.participant;

import domain.card.Card;
import domain.card.CardsSnapshot;

public class Dealer extends Participant {
    private final static String DEFAULT_NAME = "딜러";
    private static final int HIT_THRESHOLD = 16;

    public Dealer() {
        super(new Name(DEFAULT_NAME));
    }

    public CardsSnapshot startingHandInfo() {
        return firstCardInfo();
    }

    public boolean canReceive() {
        return getScore() <= HIT_THRESHOLD;
    }

    public Card upCard() {
        return firstCardInHand();
    }
}
