package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Score;
import java.util.List;
import java.util.Objects;

public class Participant {
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

    public Score calculateScore() {
        return cards.sumAllCards();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public String getName() {
        return this.name.getValue();
    }

    public List<Card> getAllCards() {
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
