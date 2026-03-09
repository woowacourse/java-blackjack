package domain.participant;

import domain.card.Card;
import domain.card.Cards;

public abstract class Participant {
    protected final Name name;
    protected final Cards cards;

    public Participant(String name) {
        this.name = new Name(name);
        this.cards = new Cards();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int getTotalSum() {
        return cards.getTotalSum();
    }

    public Name getName() {
        return name;
    }
}
