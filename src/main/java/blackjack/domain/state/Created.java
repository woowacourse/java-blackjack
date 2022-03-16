package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.game.BattingMoney;

public abstract class Created implements State {

    protected final Cards cards;
    protected final BattingMoney battingMoney;

    protected Created(final Cards cards, final BattingMoney battingMoney) {
        this.cards = cards;
        this.battingMoney = battingMoney;
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
