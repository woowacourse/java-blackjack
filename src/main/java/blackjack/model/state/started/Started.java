package blackjack.model.state.started;

import blackjack.model.card.Cards;
import blackjack.model.state.State;
import java.util.List;

public abstract class Started implements State {

    protected final Cards cards;

    protected Started() {
        this.cards = new Cards();
    }

    protected Started(Cards cards) {
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
