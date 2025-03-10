package blackjack.model.player;

import blackjack.model.card.Cards;

public class Dealer extends Player {

    private static final int DRAWABLE_POINT = 17;
    private static final String DEFAULT_NAME = "딜러";

    public Dealer(final String name) {
        super(name);
    }

    public Dealer() {
        this(DEFAULT_NAME);
    }

    public boolean isDraw(final User user) {
        if (user.isBust() || isBust()) {
            return false;
        }
        return calculateOptimalPoint() == user.calculateOptimalPoint();
    }

    public boolean isWin(final User user) {
        if (user.isBust()) {
            return true;
        }
        if (isBust()) {
            return false;
        }
        return calculateOptimalPoint() > user.calculateOptimalPoint();
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
