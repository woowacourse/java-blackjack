package blackjack.strategy;

import blackjack.domain.participant2.Participant;

@FunctionalInterface
public interface CardsViewStrategy2 {

    void print(Participant participant);
}
