package domain;

import java.util.List;

public final class Dealer extends Player {

    private static final String DEALER_NAME = "딜러";
    public static final int CARD_RENEWAL_COUNT = 1;

    private Dealer(final String name, final Cards cards) {
        super(name, cards);
    }

    public static Dealer create() {
        return new Dealer(DEALER_NAME, new Cards());
    }

    @Override
    List<Card> revealCards() {
        return cards.getCards().subList(0, CARD_RENEWAL_COUNT);
    }
}
