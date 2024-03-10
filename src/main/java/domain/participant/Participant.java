package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import java.util.List;
import java.util.Objects;

public class Participant {
    private final Name name;
    private final Cards cards;

    public Participant(String name) {
        this.name = new Name(name);
        this.cards = new Cards();
    }

    public void add(Card card) {
        cards.addCard(card);
    }

    public int calculateScore() {
        return cards.sumAll();
    }

    public String getName() {
        return this.name.getValue();
    }

    public List<Card> getCardHand() {
        return cards.getCards();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Participant other) {
            return this.name.equals(other.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
