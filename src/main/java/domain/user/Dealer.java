package domain.user;

import domain.card.Card;
import domain.game.Score;
import domain.state.State;

import java.util.List;

public class Dealer {

    private static final Score hitScoreLimit = new Score(16);

    private final Participant participant;

    public Dealer(String dealerName) {
        this.participant = new Participant(dealerName);
    }

    public boolean canHit() {
        return participantScore().isLessThanOrEqual(hitScoreLimit);
    }
    private Score participantScore() {
        return getState().score();
    }

    public State hit(Card card) {
        if (canHit()) {
            return participant.hit(card);
        }

        throw new IllegalStateException("카드를 뽑을 수 없는 상태");
    }

    public State stay() {
        return participant.stay();
    }

    public String getName() {
        return participant.getName().getValue();
    }

    public State getState() {
        return participant.getState();
    }

    public List<Card> cards() {
        return participant.cards();
    }

    public Score getScore() {
        return getState().score();
    }
}
