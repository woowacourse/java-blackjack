package blackjack.domain.participant.player;

import blackjack.controller.AddCardOrNot;
import blackjack.domain.participant.Participant;

@FunctionalInterface
public interface CardDecisionStrategy {
    AddCardOrNot decide(Participant participant);
}
