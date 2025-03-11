package blackjack.model.player;

import blackjack.model.card.Cards;

public class Dealer extends BlackJackPlayer {

    private static final int DRAWABLE_POINT = 17;
    private static final String DEFAULT_NAME = "딜러";

    public Dealer(final String name) {
        super(name);
    }

    public Dealer() {
        this(DEFAULT_NAME);
    }

    public boolean isDraw(final Player player) {
        if (player.isBust() || isBust()) {
            return false;
        }
        return calculateOptimalPoint() == player.calculateOptimalPoint();
    }

    public boolean isWin(final Player player) {
        if (player.isBust()) {
            return true;
        }
        if (isBust()) {
            return false;
        }
        return calculateOptimalPoint() > player.calculateOptimalPoint();
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
