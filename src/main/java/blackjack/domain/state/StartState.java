package blackjack.domain.state;

import blackjack.domain.Cards;
import blackjack.domain.Money;
import java.util.Objects;

public abstract class StartState implements State {

    Cards cards;

    public StartState(Cards cards) {
        this.cards = cards;
    }

    public abstract int profit(Money money);

    @Override
    public Cards cards() {
        return this.cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StartState)) {
            return false;
        }
        StartState that = (StartState) o;
        return Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
