package domain.state.generator;

import domain.card.Hand;
import domain.state.State;

public interface FinishedStateGenerator {
    boolean supports(Hand hand);

    State create(Hand hand);
}
