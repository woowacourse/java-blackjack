package domain;

import java.util.Collections;
import java.util.List;
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

    public Result competeWith(Participant otherPlayer) {
        if (otherPlayer.isBusted()) {
            return Result.WIN;
        }

        if (this.isBusted()) {
            return Result.LOSE;
        }

        BlackjackScore blackjackScore = BlackjackScore.from(cards);
        BlackjackScore otherBlackjackScore = BlackjackScore.from(otherPlayer.cards);
        return blackjackScore.compete(otherBlackjackScore);
    }

    public abstract List<Card> getInitialOpeningCards();

    public abstract boolean isAbleToReceiveCard();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return participantName.equals(that.participantName) && getCards().equals(that.getCards());
    }

    @Override
    public int hashCode() {
        return Objects.hash(participantName, getCards());
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }

    public String getName() {
        return participantName.getName();
    }
}
