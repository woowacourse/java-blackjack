package blackjack.model.state.created;

import blackjack.model.card.Cards;
import blackjack.model.state.State;
import java.util.List;

public abstract class Created implements State {

    protected final Cards cards;

    protected Created() {
        this.cards = new Cards();
    }

    protected Created(Cards cards) {
        this.cards = cards;
    }

    @Override
    public List<String> getCards() {
        return cards.getCardNames();
    }

    @Override
    public int getScore() {
        return cards.sumScore();
    }
}
