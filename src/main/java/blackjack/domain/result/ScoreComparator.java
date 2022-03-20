package blackjack.domain.result;

import blackjack.domain.participant.Participant;

@FunctionalInterface
public interface ScoreComparator {

    boolean compare(Participant user, Participant dealerScore);
}
