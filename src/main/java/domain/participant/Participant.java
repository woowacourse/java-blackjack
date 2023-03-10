package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import java.util.List;

public abstract class Participant {

    private final Name name;
    private Cards cards;

    public Participant(final Name name) {
        this.name = name;
        this.cards = new Cards();
    }

    abstract boolean isHittable();

    public void receiveInitialCards(List<Card> cards) {
        this.cards = this.cards.receiveInitialCards(cards);
    }

    public void receiveCard(Card card) {
        this.cards = this.cards.receiveCard(card);
    }

    public Score calculateScore() {
        return cards.calculateScore();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return List.copyOf(cards.getCards());
    }

    public int getScore() {
        return calculateScore().getValue();
    }
}
