package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.game.BettingMoney;

public abstract class Created implements State {

    protected Cards cards;
    protected final BettingMoney bettingMoney;

    protected Created(final Cards cards, final BettingMoney bettingMoney) {
        this.cards = cards;
        this.bettingMoney = bettingMoney;
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public boolean isSameStateWith(final Class<? extends State> state) {
        return this.getClass().equals(state);
    }
}
