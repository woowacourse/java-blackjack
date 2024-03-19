package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import domain.participant.name.Name;
import java.util.List;
import java.util.Objects;

public abstract class Participant {
    private final Name name;
    private final Cards cards;

    public Participant(String name) {
        this.name = new Name(name);
        this.cards = new Cards();
    }

    public void receiveInitialCards(Card first, Card second) {
        receiveAdditionalCard(first);
        receiveAdditionalCard(second);
    }

    public void receiveAdditionalCard(Card card) {
        cards.addCard(card);
    }

    public int calculateScore() {
        return cards.sumAllCards();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public abstract boolean isNotFinished();

    public String getName() {
        return this.name.getValue();
    }

    public List<Card> getAllCards() {
        return cards.getCards();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant other = (Participant) o;
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
