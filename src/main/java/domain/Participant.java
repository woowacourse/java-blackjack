package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Participant {

    protected final String name;
    protected final List<Card> cards = new ArrayList<>();

    public Participant(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getReadyCards() {
        if (cards.size() < 2) {
            throw new IllegalStateException();
        }
        return List.of(cards.get(0), cards.get(1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

