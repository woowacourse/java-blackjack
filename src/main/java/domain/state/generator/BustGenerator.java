package domain.state.generator;

import domain.card.Hand;
import domain.state.StartStateGenerator;
import domain.state.State;
import domain.state.finished.Bust;

public class BustGenerator implements StartStateGenerator {
    @Override
    public boolean supports(Hand hand) {
        return hand.isBust();
    }

    @Override
    public State create(Hand hand) {
        return new Bust(hand);
    }
}
