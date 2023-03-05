package domain.participant;

import domain.card.Cards;

import java.util.List;

public final class Dealer extends Participant {

    public static final int STANDARD_OF_NEED_MORE_CARD = 16;

    private Dealer(final Name name) {
        super(name);
    }

    private Dealer(final Name name, final Cards cards) {
        super(name, cards);
    }

    public static Dealer create() {
        return new Dealer(Name.of(DEALER_NAME));
    }

    public boolean needMoreCard() {
        return cards.isLowerThan(STANDARD_OF_NEED_MORE_CARD) && this.isBusted();
    }

    public Cards getFirstCard() {
        return Cards.create(List.of(cards.getFirstCard()));
    }
}
