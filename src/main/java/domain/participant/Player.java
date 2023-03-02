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
        return getTotalValue() <= LIMIT_TAKE_CARD_VALUE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getTotalValue() {
        int totalValue = 0;
        for (Card card : cards) {
            totalValue += card.getValue();
        }
        return totalValue;
    }
}
