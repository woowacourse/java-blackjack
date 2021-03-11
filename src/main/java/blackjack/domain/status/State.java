package blackjack.domain.status;

import blackjack.domain.card.Card;

import java.util.Arrays;
import java.util.List;

public abstract class State {
    protected List<Card> cards;

    public State(Card... cards) {
        this(Arrays.asList(cards));
    }

    public State(List<Card> cards) {
        this.cards = cards;
    }
}
