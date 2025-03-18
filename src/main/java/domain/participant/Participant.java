package domain.participant;

import domain.TrumpCard;
import domain.participant.state.State;
import domain.participant.state.hand.Score;
import java.util.List;

public abstract class Participant {

    protected State state;

    protected Participant(State state) {
        validate(state);
        this.state = state;
    }

    private void validate(State state) {
        validateNotNull(state);
    }

    private void validateNotNull(State state) {
        if (state == null) {
            throw new IllegalArgumentException("참가자는 상태를 가져야합니다.");
        }
    }

    public boolean isHitAllowed() {
        return state.canHit();
    }

    public void receiveCard(TrumpCard card) {
        state = state.draw(card);
    }

    public List<TrumpCard> retrieveCards() {
        return state.retrieveCards();
    }

    public Score calculateScore() {
        return state.calculateScore();
    }
}
