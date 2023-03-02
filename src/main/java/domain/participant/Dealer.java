package domain.participant;

import java.util.Collections;
import java.util.List;

import domain.card.Card;

public class Dealer extends Participant {
    private static final int LIMIT_TAKE_CARD_VALUE = 17;
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    public boolean checkCardsCondition() {
        return getMaxSum() < LIMIT_TAKE_CARD_VALUE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
