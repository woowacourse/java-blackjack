package domain.state.generator;

import domain.card.Hand;
import domain.state.State;
import domain.state.finished.Bust;

public class BustGenerator implements FinishedStateGenerator {
    @Override
    public boolean supports(Hand hand) {
        return Bust.isBust(hand);
    }

    @Override
    public State create(Hand hand) {
        return new Bust(hand);
    }
}
