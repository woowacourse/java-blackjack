package blackjack.domain.participant.state;

import blackjack.domain.carddeck.Card;
import java.util.ArrayList;
import java.util.List;

public abstract class AfterInitDraw implements State {

    protected final List<Card> cards;

    public AfterInitDraw(final List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public List<Card> cards() {
        return new ArrayList<>(this.cards);
    }
}
