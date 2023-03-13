package domain.participant;

import domain.card.Card;
import domain.card.Cards;

public class Dealer extends Participant {

    public static final String NAME = "딜러";
    private static final int STANDARD_FOR_HIT = 16;


    public Dealer(Cards cards) {
        super(NAME, cards);
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }

    @Override
    public boolean canAddCard() {
        return cards.getScore() <= STANDARD_FOR_HIT;
    }
}
