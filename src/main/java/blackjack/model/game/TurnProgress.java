package blackjack.model.game;

import blackjack.model.participant.Participant;

@FunctionalInterface
public interface TurnProgress {

    void show(Participant participant);
}
