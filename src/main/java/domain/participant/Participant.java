package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import java.util.List;

public abstract class Participant {
    protected final Name name;
    protected final Cards cards;

    public Participant(String name) {
        this.name = new Name(name);
        this.cards = new Cards();
    }

    public String getName() {
        return name.toString();
    }

    public List<String> getCardNames() {
        return cards.getCardNames();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int getTotalSum() {
        return cards.getTotalSum();
    }
}
