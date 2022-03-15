package blackjack.model;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;

public abstract class State {
    protected static final String HIT = "Hit";
    protected static final String BLACKJACK = "Blackjack";
    protected static final String BUST = "Bust";

    protected final Cards cards;
    protected String state;

    public State() {
        this.cards = new Cards();
        this.state = HIT;
    }

    protected State(Cards cards, String state) {
        this.cards = cards;
        this.state = state;
    }

    public String state() {
        choiceState();
        return state;
    }

    protected abstract void choiceState();

    public abstract State addCard(Card card);
}
