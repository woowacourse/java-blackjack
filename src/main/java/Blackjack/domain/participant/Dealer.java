package Blackjack.domain.participant;

import Blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends Participant {

    private static final int ADD_CARD_UPPER_BOUND = 16;

    public Dealer(final String name) {
        super(name);
    }

    @Override
    public List<Card> getInitialCards() {
        final List<Card> cards = super.getCards();
        return cards.subList(0, 1);
    }

    @Override
    public boolean ableToAddCard() {
        final int cardsScore = cards.calculateScore();
        return cardsScore <= ADD_CARD_UPPER_BOUND;
    }
}
