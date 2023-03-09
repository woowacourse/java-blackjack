package domain;

import java.util.Objects;

public abstract class Participant {
    protected final ParticipantName participantName;
    protected final Cards cards = Cards.getDefault();

    protected Participant(ParticipantName participantName) {
        this.participantName = participantName;
    }

    public void receive(Card card) {
        cards.add(card);
    }

    public void receive(Cards cards) {
        for (Card card : cards.getCards()) {
            this.receive(card);
        }
    }

    public BlackjackScore calculateBlackjackScore() {
        return BlackjackScore.from(cards);
    }

    public boolean isBusted() {
        return calculateBlackjackScore().isGreaterThan(BlackjackScore.getMaxScore());
    }

    public abstract Cards getInitialOpeningCards();

    public abstract boolean isAbleToReceiveCard();

    public int getCurrentCardAmount() {
        return cards.getCards().size();
    }

    @Override
    public int hashCode() {
        return Objects.hash(participantName, getCards());
    }

    public Cards getCards() {
        return cards;
    }

    public String getName() {
        return participantName.getName();
    }
}
