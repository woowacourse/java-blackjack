package domain.state;

import domain.card.Deck;
import domain.card.vo.Card;
import domain.hitStrategy.HitStrategy;
import domain.participants.Hand;
import domain.participants.Participant;
import java.util.List;

public abstract class State {
    private static final Integer BLACKJACK_SCORE = 21;
    private final Hand hand;
    private final Participant participant;

    protected State(Hand hand, Participant participant) {
        this.hand = hand;
        this.participant = participant;
    }

    public static State getStartState(Hand hand, Participant participant, HitStrategy hitStrategy) {
        if (hand.getScore().equals(BLACKJACK_SCORE)) {
            return new BlackJack(hand, participant);
        }
        return new Hit(hand, participant, hitStrategy);
    }

    abstract public boolean isFinished();

    public State drawCard(Deck deck, boolean toHit) {
        if (!toHit) {
            return new Stay(hand, participant);
        }
        hand.add(deck.drawCard());
        if (hand.isBurt()) {
            return new Bust(hand, participant);
        }
        if (isFinished()) {
            return new Stay(hand, participant);
        }
        return new Hit(hand, participant, participant.getHitStrategy());
    }

    public Integer getScore() {
        return hand.getScore();
    }

    public Integer getProfit(State dealerState) {
        return participant.getProfit(Result.getResult(dealerState, this));
    }

    public String getParticipantName() {
        return participant.getName();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }
}
