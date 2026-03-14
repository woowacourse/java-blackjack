package domain.state.generator;

import domain.card.Hand;
import domain.state.StartStateGenerator;
import domain.state.State;
import domain.state.finished.Blackjack;

public class BlackjackGenerator implements StartStateGenerator {
    @Override
    public boolean supports(Hand hand) {
        return Blackjack.isBlackJack(hand);
    }

    @Override
    public State create(Hand hand) {
        return new Blackjack(hand);
    }
}
