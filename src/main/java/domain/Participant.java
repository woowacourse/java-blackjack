package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private final String name;
    private final List<Card> cards;

    public Participant(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getName() {
        return name;
    }

    public abstract List<Card> getShownCard();

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public String toString() {
        return "Participant{" +
            "name='" + name + '\'' +
            ", cards=" + cards +
            '}';
    }
}
