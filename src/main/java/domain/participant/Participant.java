package domain.participant;

import domain.card.Card;
import domain.card.Cards;
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

    public boolean isGreaterThan(int score) {
        return cards.isGreaterThan(score);
    }

    public boolean isLessThan(int score) {
        return cards.isLessThan(score);
    }

    Cards getCards() {
        return cards;
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
