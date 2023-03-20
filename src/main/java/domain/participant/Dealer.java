package domain.participant;

import domain.card.Cards;

public class Dealer extends Participant {

    private static final int STANDARD_TO_GET_ONE_MORE_CARD = 16;

    public Dealer(Name name, Cards cards) {
        super(name, cards, new Money(0));
    }

    @Override
    public boolean canReceiveOneMoreCard() {
        return cards.calculateSum() <= STANDARD_TO_GET_ONE_MORE_CARD;
    }
}
