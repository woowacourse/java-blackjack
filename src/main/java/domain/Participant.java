package domain;

import java.util.List;

public abstract class Participant {
    protected final Hand hand;
    protected final ParticipantName participantName;

    protected Participant(String name, Hand hand) {
        this.hand = hand;
        this.participantName = new ParticipantName(name);
    }

    public abstract List<Card> showInitialCard();

    public String getName() {
        return participantName.name();
    }

    public List<Card> showOwnCards() {
        return hand.showCards();
    }

    public int getOwnCardsSum() {
        return this.hand.calculateCardScoreSum();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }
}