package domain;

import domain.card.Card;
import java.util.List;

public class Dealer extends Participant  {

    private static final int ADD_CARD_UPPER_BOUND = 16;
    private static final String DEALER_NAME = "딜러";

    protected Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public List<Card> getInitialCards() {
        List<Card> cards = super.getCards();
        return cards.subList(0, 1);
    }

    @Override
    public boolean ableToAddCard() {
        int cardsScore = cards.calculateScore();
        return cardsScore <= ADD_CARD_UPPER_BOUND;
    }
}
