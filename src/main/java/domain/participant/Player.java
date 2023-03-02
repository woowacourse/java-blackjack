package domain.participant;

import java.util.Collections;
import java.util.List;

import domain.card.Card;

public class Player extends Participant {

    private static final int LIMIT_TAKE_CARD_VALUE = 21;

    public Player(Name name) {
        super(name);
    }

    public boolean checkCardsCondition() {
        return getMaxSum() <= LIMIT_TAKE_CARD_VALUE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
