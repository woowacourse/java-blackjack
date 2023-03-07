package blackjack.domain.participant.player;

import blackjack.domain.participant.Participant;

public interface CardDisplayStrategy {
    void display(Participant participant);
}
