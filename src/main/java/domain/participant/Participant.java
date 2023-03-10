package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import domain.status.Status;
import domain.status.initial.Ready;
import java.util.List;

public abstract class Participant {

    private final Name name;
    private Cards cards;
    private Status status;

    public Participant(final Name name) {
        this.name = name;
        this.cards = new Cards();
        this.status = new Ready();
    }

    public Participant(final Name name, final Status status) {
        this.name = name;
        this.status = status;
    }

    abstract boolean isHittable();

    public void receiveInitialCards(Cards cards) {
        this.cards = cards;
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
