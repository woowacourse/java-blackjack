package domain.participant;

import domain.card.Card;
import domain.card.CardHand;
import java.util.List;
import java.util.Objects;

public class Participant {
    private final Name name;
    private final CardHand cardHand;

    public Participant(String name) {
        this.name = new Name(name);
        this.cardHand = new CardHand();
    }

    public void add(Card card) {
        cardHand.addCard(card);
    }

    public int calculateScore() {
        return cardHand.sumAll();
    }

    public String getName() {
        return this.name.getValue();
    }

    public List<Card> getCardHand() {
        return cardHand.getCards();
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
