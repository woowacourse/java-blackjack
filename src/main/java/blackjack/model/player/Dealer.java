package blackjack.model.player;

import blackjack.model.card.Cards;

public class Dealer extends Player {

    private static final int DRAWABLE_POINT = 17;

    public Dealer(final String name) {
        super(name);
    }

    @Override
    public Cards openInitialCards() {
        return new Cards(cards.getFirst());
    }

    @Override
    public boolean canDrawMoreCard() {
        return getMinimumPoint() < DRAWABLE_POINT;
    }
}
