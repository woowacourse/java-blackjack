package domain.state;

import domain.card.vo.Card;
import domain.participants.Hand;
import domain.participants.Participant;
import java.util.List;

public abstract class State {
    private final Hand hand;
    private final Participant participant;

    protected State(Hand hand, Participant participant) {
        this.hand = hand;
        this.participant = participant;
    }

    abstract public boolean isFinished();

    public State drawCard(Card card, boolean toHit) {
        if (!toHit) {
            return new Stay(hand, participant);
        }
        hand.add(card);
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
