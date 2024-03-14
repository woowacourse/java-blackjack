package blackjack.domain.participant.rule;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Participant;

@FunctionalInterface
public interface DrawRule<P extends Participant> {
    void play(P p, Deck deck);
}
