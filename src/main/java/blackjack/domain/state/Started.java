package blackjack.domain.state;

import blackjack.domain.Cards;
import blackjack.domain.Money;

public abstract class Started implements State {

    Cards cards;

    public Started(Cards cards) {
        this.cards = cards;
    }

    public abstract int profit(Money money);

    @Override
    public Cards getCards() {
        return this.cards;
    }
}
