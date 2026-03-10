package domain.state;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Hand;
import domain.participants.Participant;
import java.util.List;

public abstract class State {
    private final Hand hand;
    private final Participant participant;

    abstract public boolean isFinished();

    protected State(Hand hand, Participant participant) {
        this.hand = hand;
        this.participant = participant;
    }

    public State drawCard(Deck deck, boolean toHit) {
        if (!toHit) {
            return new Stay(hand, participant);
        }
        hand.add(deck.drawCard());
        if (hand.isBurst()) {
            return new Burst(hand, participant);
        }
        if (isFinished()) {
            return new Stay(hand, participant);
        }
        return new Hit(hand, participant, participant.getHitStrategy());
    }

    public Integer getScore() {
        return hand.getScore();
    }

    public String getParticipantName() {
        return participant.getName();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }
}

