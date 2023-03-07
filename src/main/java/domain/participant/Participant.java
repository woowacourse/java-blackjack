package domain.participant;

import domain.card.Card;

import java.util.List;

public class Participant {

    protected static final ParticipantName DEALER_NAME = ParticipantName.create("딜러");

    private final ParticipantName name;
    protected final ParticipantCard card;

    protected Participant(final String name) {
        this.name = ParticipantName.create(name);
        this.card = ParticipantCard.create();
    }

    public final void addCard(final Card card) {
        this.card.addCard(card);
    }

    public final List<Card> getCard() {
        return List.copyOf(card.getCards());
    }

    public final int calculateScore() {
        return card.calculateScore();
    }

    public final boolean isBust() {
        return card.isBust();
    }

    public final boolean isBlackJack() {
        return card.isBlackJack();
    }

    public final String getName() {
        return name.getName();
    }
}
