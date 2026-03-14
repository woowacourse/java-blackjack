package domain.state;

import domain.card.Hand;

public interface StartStateGenerator {
    boolean supports(Hand hand);

    State create(Hand hand);
}
