package blackjack.strategy;

import blackjack.domain.participant.Participant;

@FunctionalInterface
public interface CardsViewStrategy {

    void print(Participant participant);
}
