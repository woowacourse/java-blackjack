package domain.participant.state;

import domain.participant.state.hand.Score;
import domain.participant.state.hand.Hand;

public class Bust extends Finished {

    public Bust(Hand hand) {
        super(hand);
        validate(hand);
    }

    private void validate(Hand hand) {
        validateScore(hand);
    }

    private void validateScore(Hand hand) {
        if (hand.calculateScore() != Score.BUST) {
            throw new IllegalArgumentException("버스트여야 합니다.");
        }
    }
}
