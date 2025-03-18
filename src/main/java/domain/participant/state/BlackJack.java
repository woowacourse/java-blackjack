package domain.participant.state;

import domain.participant.state.hand.Score;
import domain.participant.state.hand.Hand;

public class BlackJack extends Finished {

    public BlackJack(Hand hand) {
        super(hand);
        validate(hand);
    }

    private void validate(Hand hand) {
        validateScore(hand);
    }

    private void validateScore(Hand hand) {
        if (hand.calculateScore() != Score.BLACKJACK) {
            throw new IllegalArgumentException("블랙잭이여야 합니다.");
        }
    }
}
