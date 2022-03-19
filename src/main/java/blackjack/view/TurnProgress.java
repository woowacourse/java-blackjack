package blackjack.view;

import blackjack.model.participant.Participant;

@FunctionalInterface
public interface TurnProgress {

    void show(Participant participant);
}
